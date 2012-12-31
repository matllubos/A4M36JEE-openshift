package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * <p>Reservation is cancelled, required operation is not allowed.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "ReservationCancelledExceptionFault" )
public class ReservationCancelledException extends RuntimeException implements Serializable {

    public ReservationCancelledException() {
    }

    public ReservationCancelledException( final String message ) {
        super( message );
    }

    public ReservationCancelledException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
