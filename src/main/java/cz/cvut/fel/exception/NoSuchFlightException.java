package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * <p>No such flight exists.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "NoSuchFlightFault" )
public class NoSuchFlightException extends RuntimeException implements Serializable {

    public NoSuchFlightException( final String message ) {
        super( message );
    }
}
