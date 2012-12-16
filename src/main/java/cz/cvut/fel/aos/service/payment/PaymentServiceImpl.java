package cz.cvut.fel.aos.service.payment;

import cz.cvut.fel.aos.model.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.apache.geronimo.mail.util.Hex;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.*;
import java.security.MessageDigest;
import java.util.Date;

/**
 * <p>Web service přijímající platby rezervací.</p>
 *
 * @author Karel Cemus
 */
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Reservation payVisa( final long reservationId, final String password, final String cardName, final long creditCard, final Date validUntil, final int verificationCode ) throws NoSuchReservationException, InvalidPaymentException, SecurityException {

        // načti rezervaci
        Reservation reservation = loadReservation( reservationId, password );

        if ( reservation.isCanceled() ) { // nelze platit zrušenou rezervaci
            PaymentServiceImpl.log.info( "Rejected credit card '{}' bound to '{}'. Reservation is cancelled.", creditCard, cardName );
            throw new InvalidPaymentException( "Reservation is cancelled." );
        }

        if ( creditCard % 1000 != verificationCode ) { // kontrola platnosti ověřovacího kódu, musí to být poslední 3 číslice z čísla kreditky
            PaymentServiceImpl.log.info( "Rejected credit card '{}' bound to '{}'. Verification code is wrong.", creditCard, cardName );
            throw new InvalidPaymentException( "Verification code is wrong." );
        }

        if ( validUntil.getTime() < new Date().getTime() ) { // zkontroluj platnost karty
            PaymentServiceImpl.log.info( "Rejected credit card '{}' bound to '{}'. Card expired.", creditCard, cardName );
            throw new InvalidPaymentException( "Credit card expired." );
        }

        // spočítej zbývající částku k zaplacení
        int amount = Math.max( reservation.getCost() - reservation.getPaid(), 0 );

        if ( amount == 0 ) { // neplatit již zaplacené rezervace
            PaymentServiceImpl.log.info( "Payment for reservation with ID '{}' was ignored, reservation is already paid.", reservationId );
            return reservation;
        }

        // zaloguj přijetí platební karty
        PaymentServiceImpl.log.info( "Accepted credit card '{}' bound to '{}'. There was transferred '{}' money.", new Object[]{ creditCard, cardName, amount } );

        // proveď převod
        reservation.setPaid( reservation.getPaid() + amount );

        PaymentServiceImpl.log.info( "Reservation with ID '{}' received '{}' money. There is '{}'.", new Object[]{ reservation.getId(), amount, reservation.getPaid() } );

        return reservation;
    }

    @Override
    @Transactional
    public Reservation transferFromReservation( final long reservationIdFrom, final String passwordFrom, final long reservationIdTo, final String passwordTo ) throws InvalidPaymentException {

        // rezervace, ze které se převádí peníze
        Reservation reservationFrom = loadReservation( reservationIdFrom, passwordFrom );

        // rezervace, na kterou se převádí peníze
        Reservation reservationTo = loadReservation( reservationIdTo, passwordTo );

        if ( !reservationFrom.isCanceled() ) { // možné převádět pouze ze zrušených rezervací
            PaymentServiceImpl.log.info( "Transfer from reservation with ID '{}' was rejected due to the reservation is not cancelled.", reservationIdFrom );
            throw new InvalidPaymentException( String.format( "Transfer from reservation with ID '%d' was rejected due to the reservation is not cancelled.", reservationIdFrom ) );
        }

        if ( reservationTo.isCanceled() ) { // možné převádět pouze na aktivní rezervace
            PaymentServiceImpl.log.info( "Transfer to reservation with ID '{}' was rejected due to the reservation is cancelled.", reservationIdTo );
            throw new InvalidPaymentException( String.format( "Transfer to reservation with ID '%d' was rejected due to the reservation is cancelled.", reservationIdTo ) );
        }

        if ( reservationFrom.getPaid() == 0 ) { // zjisti, jestli zbývají nějaké peníze v rezervaci
            PaymentServiceImpl.log.info( "Transfer from reservation with ID '{}' to reservation with ID '{}' was rejected due to account is empty.", reservationIdFrom, reservationIdTo );
            throw new InvalidPaymentException( String.format( "Transfer from reservation with ID '%d' was rejected due to account is empty.", reservationIdFrom ) );
        }

        // spočítej zbývající částku k zaplacení
        int amount = Math.max( reservationTo.getCost() - reservationTo.getPaid(), 0 );
        // lze převést jen tolik, kolik je na účtu
        amount = Math.min( reservationFrom.getPaid(), amount );

        if ( amount == 0 ) { // neplatit již zaplacené rezervace
            PaymentServiceImpl.log.info( "Payment for reservation with ID '{}' was ignored, reservation is already paid.", reservationIdTo );
            return reservationTo;
        }

        // zaloguj přijetí platební karty
        PaymentServiceImpl.log.info( "Accepted credit transfer from ID '{}' to ID '{}'. There was transferred '{}' money.", new Object[]{ reservationIdFrom, reservationIdTo, amount } );

        // proveď převod
        reservationFrom.setPaid( reservationFrom.getPaid() - amount );
        reservationTo.setPaid( reservationTo.getPaid() + amount );

        PaymentServiceImpl.log.info( "Reservation with ID '{}' sent '{}' money. There is '{}'.", new Object[]{ reservationFrom.getId(), amount, reservationFrom.getPaid() } );
        PaymentServiceImpl.log.info( "Reservation with ID '{}' received '{}' money. There is '{}'.", new Object[]{ reservationTo.getId(), amount, reservationTo.getPaid() } );

        return reservationTo;
    }

    private Reservation loadReservation( final long id, final String password ) throws NoSuchReservationException, SecurityException {
        Reservation reservation = em.find( Reservation.class, id );

        if ( reservation == null ) { // rezervace neexistuje
            throw new NoSuchReservationException( String.format( "Reservation with ID '%d' doesn't exists.", id ) );
        }

        // zkontroluj přístup k rezervaci
        if ( reservation != null && !reservation.getPassword().equalsIgnoreCase( hash( password ) ) ) {
            throw new SecurityException( String.format( "Access to reservation with ID '%d' is forbidden. Password is incorrect.", reservation.getId() ) );
        }

        return reservation;
    }

    private String hash( String password ) {
        try {
            byte[] bytesOfMessage = ( "some salt 12345" + password ).getBytes( "UTF-8" );
            MessageDigest md = MessageDigest.getInstance( "SHA-1" );
            byte[] digest = md.digest( bytesOfMessage );
            return new String( Hex.encode( digest ) );
        } catch ( Exception e ) {
            return password;
        }
    }

}
