package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;

/**
 * <p>Reservation is not fully paid yet, operation is not allowed.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "ReservationNotPaidFault" )
public class ReservationNotPaidException extends Exception {

    public ReservationNotPaidException() {
    }

    public ReservationNotPaidException( final String message ) {
        super( message );
    }

    public ReservationNotPaidException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
