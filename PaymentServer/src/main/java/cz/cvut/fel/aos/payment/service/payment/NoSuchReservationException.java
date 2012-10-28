package cz.cvut.fel.aos.payment.service.payment;

import javax.xml.ws.WebFault;

/**
 * <p>Chyba indikující neplatné ID rezervace.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "NoSuchReservationFault" )
public class NoSuchReservationException extends RuntimeException {

    public NoSuchReservationException() {
    }

    public NoSuchReservationException( final String message ) {
        super( message );
    }

    public NoSuchReservationException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
