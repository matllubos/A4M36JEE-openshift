package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;

/**
 * <p>Reservation is not Cancelled, required operation is not allowed.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "ReservationNotCancelledExceptionFault" )
public class ReservationNotCancelledException extends Exception {

    public ReservationNotCancelledException() {
    }

    public ReservationNotCancelledException( final String message ) {
        super( message );
    }

    public ReservationNotCancelledException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
