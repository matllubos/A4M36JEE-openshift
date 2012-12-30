package cz.cvut.fel.service;

import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * <p>No such destination exists.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "NoSuchDestinationFault" )
public class NoSuchDestinationException extends RuntimeException implements Serializable {

    public NoSuchDestinationException( final String message ) {
        super( message );
    }
}
