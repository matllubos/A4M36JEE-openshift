package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;

/**
 * <p>No such reservation exists.</p>
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
