package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * <p>Reservation is not fully paid yet, operation is not allowed.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "ReservationNotPaidFault" )
public class ReservationNotPaidException extends RuntimeException implements Serializable {

    public ReservationNotPaidException() {
    }

    public ReservationNotPaidException( final String message ) {
        super( message );
    }

    public ReservationNotPaidException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
