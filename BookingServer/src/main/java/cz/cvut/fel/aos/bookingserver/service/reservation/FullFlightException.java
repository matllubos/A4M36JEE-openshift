package cz.cvut.fel.aos.bookingserver.service.reservation;

import javax.xml.ws.WebFault;

/**
 * <p>Požadovaný let je již plný</p>
 *
 * @author Karel Cemus
 */
@WebFault(name = "FullFlightFault")
public class FullFlightException extends RuntimeException {

    public FullFlightException() {
    }

    public FullFlightException( final String message ) {
        super( message );
    }

    public FullFlightException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
