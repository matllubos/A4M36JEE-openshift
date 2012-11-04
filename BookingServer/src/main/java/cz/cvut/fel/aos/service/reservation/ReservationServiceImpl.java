package cz.cvut.fel.aos.service.reservation;

import cz.cvut.fel.aos.model.Flight;
import cz.cvut.fel.aos.model.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.apache.geronimo.mail.util.Hex;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.MessageDigest;

/** @author Karel Cemus */
@Slf4j
@WebService( endpointInterface = "cz.cvut.fel.aos.service.reservation.ReservationService" )
public class ReservationServiceImpl implements ReservationService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Reservation find( long reservation, String password ) throws SecurityException {
        Reservation entity = em.find( Reservation.class, reservation );

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        return entity;
    }

    @Override
    @Transactional
    public Reservation create( String flightNumber, String password, int count ) throws FullFlightException {

        // získej let
        Flight flight = em.createNamedQuery( "Flight.findByNumber", Flight.class ).setParameter( "number", flightNumber ).getSingleResult();

        if ( flight == null ) { // chyba vstupu
            throw new IllegalStateException( String.format( "Flight with number '%s' doesn't exists.", flightNumber ) );
        }

        if ( flight.getCapacityLeft() < count ) { // nezbývá dostatek volných míst
            throw new FullFlightException( String.format( "Flight '%s' doesn't have enought capacity left.", flight ) );
        }

        // aktualizuj informace o letu
        flight.setCapacityLeft( flight.getCapacityLeft() - count );

        // vytvoř rezervaci
        Reservation entity = new Reservation();
        entity.setFlight( flight );
        entity.setCount( count );
        entity.setPaid( 0 );
        entity.setCost( flight.getCost() * count );
        entity.setCanceled( false );
        entity.setPassword( hash( password ) );
        em.persist( entity );

        ReservationServiceImpl.log.info( "Reservation with ID '{}' was successfully created.", entity.getId() );

        return entity;
    }

    @Override
    @Transactional
    public boolean cancel( long reservation, String password ) throws SecurityException {
        Reservation entity = em.find( Reservation.class, reservation );

        if ( entity == null ) // rezervace neexistuje
        {
            throw new IllegalArgumentException( "Reservation doesn't exists." );
        }

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        // already canceled
        if ( entity.isCanceled() ) return true;

        // zruš rezervaci
        entity.setCanceled( true );

        // uvolni zabraná místa v letadle
        Flight flight = entity.getFlight();
        flight.setCapacityLeft( flight.getCapacityLeft() + entity.getCount() );

        ReservationServiceImpl.log.info( "Reservation with ID '{}' was successfully canceled.", entity.getId() );

        return true;
    }

    private void checkSecurity( Reservation reservation, String password ) throws SecurityException {
        password = hash( password );
        if ( reservation != null && !reservation.getPassword().equalsIgnoreCase( password ) ) {
            throw new SecurityException( String.format( "Access to reservation with id '%d' is forbidden. Password is incorrect.", reservation.getId() ) );
        }
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
