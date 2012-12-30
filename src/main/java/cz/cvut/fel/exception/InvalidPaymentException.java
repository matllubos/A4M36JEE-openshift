package cz.cvut.fel.exception;

import javax.xml.ws.WebFault;

/**
 * <p>Invalid payment setup. Transaction failed.</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "InvalidPaymentFault" )
public class InvalidPaymentException extends RuntimeException {

    public InvalidPaymentException() {
    }

    public InvalidPaymentException( final String message ) {
        super( message );
    }

    public InvalidPaymentException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
