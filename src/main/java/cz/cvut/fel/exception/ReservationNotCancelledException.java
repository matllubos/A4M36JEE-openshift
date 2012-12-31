package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * <p>Reservation is not cancelled, required operation is not allowed.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "ReservationNotCancelledExceptionFault" )
public class ReservationNotCancelledException extends RuntimeException implements Serializable {

    public ReservationNotCancelledException() {
    }

    public ReservationNotCancelledException( final String message ) {
        super( message );
    }

    public ReservationNotCancelledException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
