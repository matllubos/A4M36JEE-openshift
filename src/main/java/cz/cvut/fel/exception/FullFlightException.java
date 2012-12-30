package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;

/**
 * <p>Flight is already full.</p>
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
