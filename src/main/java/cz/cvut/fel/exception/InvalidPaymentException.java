package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;
import java.io.Serializable;

/**
 * <p>Invalid payment setup. Transaction failed.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "InvalidPaymentFault" )
public class InvalidPaymentException extends RuntimeException implements Serializable {

    public InvalidPaymentException() {
    }

    public InvalidPaymentException( final String message ) {
        super( message );
    }

    public InvalidPaymentException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
