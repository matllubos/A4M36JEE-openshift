package cz.cvut.fel.aos.service.client;

import cz.cvut.fel.aos.service.adapter.InvalidPaymentException;
import cz.cvut.fel.aos.service.adapter.NoSuchReservationException;
import cz.cvut.fel.aos.service.adapter.Reservation;
import cz.cvut.fel.aos.service.adapter.SecurityException;
import cz.cvut.fel.aos.service.payment.PaymentServiceImplService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>Klient pro payment server</p>
 *
 * @author Karel Cemus
 */
@Service
public class PaymentService {

    private cz.cvut.fel.aos.service.payment.PaymentService client;

    public PaymentService() {
        PaymentServiceImplService factory = new PaymentServiceImplService();
        client = factory.getPaymentServiceImplPort();
    }

    public Reservation transferFromReservation( final long reservationIdFrom, final String passwordFrom, final long reservationIdTo, final String passwordTo ) throws NoSuchReservationException, SecurityException, InvalidPaymentException {
        try {
            return new Reservation( client.transferFromReservation( reservationIdFrom, passwordFrom, reservationIdTo, passwordTo ) );
        } catch ( cz.cvut.fel.aos.service.payment.NoSuchReservationException e ) {
            throw new NoSuchReservationException( e.getMessage(), e.getCause() );
        } catch ( cz.cvut.fel.aos.service.payment.SecurityException e ) {
            throw new SecurityException( e.getMessage(), e.getCause() );
        } catch ( cz.cvut.fel.aos.service.payment.InvalidPaymentException e ) {
            throw new InvalidPaymentException( e.getMessage(), e.getCause() );
        }
    }

    public Reservation payVisa( final long reservationId, final String password, final String cardName, final long creditCard, final Date validUntil, final int verificationCode ) throws NoSuchReservationException, SecurityException, InvalidPaymentException {
        try {

            return new Reservation( client.payVisa( reservationId, password, cardName, creditCard, validUntil, verificationCode ) );

        } catch ( cz.cvut.fel.aos.service.payment.NoSuchReservationException e ) {
            throw new NoSuchReservationException( e.getMessage(), e.getCause() );
        } catch ( cz.cvut.fel.aos.service.payment.SecurityException e ) {
            throw new SecurityException( e.getMessage(), e.getCause() );
        } catch ( cz.cvut.fel.aos.service.payment.InvalidPaymentException e ) {
            throw new InvalidPaymentException( e.getMessage(), e.getCause() );
        }
    }
}
