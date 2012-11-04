package cz.cvut.fel.aos.service.adapter;

import javax.xml.ws.WebFault;

/** @author Karel Cemus */
@WebFault( name = "InvalidPaymentFault" )
public class InvalidPaymentException extends Exception {

    public InvalidPaymentException() {
    }

    public InvalidPaymentException( final String message ) {
        super( message );
    }

    public InvalidPaymentException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
