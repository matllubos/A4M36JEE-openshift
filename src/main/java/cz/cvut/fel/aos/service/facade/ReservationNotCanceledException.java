package cz.cvut.fel.aos.service.facade;

import javax.xml.ws.WebFault;

/** @author Karel Cemus */
@WebFault( name = "ReservationNotCanceledExceptionFault" )
public class ReservationNotCanceledException extends Exception {

    public ReservationNotCanceledException() {
    }

    public ReservationNotCanceledException( final String message ) {
        super( message );
    }

    public ReservationNotCanceledException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
