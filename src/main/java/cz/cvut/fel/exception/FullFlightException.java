package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * <p>Flight is already full.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "FullFlightFault" )
public class FullFlightException extends RuntimeException implements Serializable {

    public FullFlightException() {
    }

    public FullFlightException( final String message ) {
        super( message );
    }

    public FullFlightException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
