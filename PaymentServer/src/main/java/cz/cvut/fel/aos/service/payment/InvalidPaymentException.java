package cz.cvut.fel.aos.service.payment;

import javax.xml.ws.WebFault;

/**
 * <p>Chyba indikující neplatné údaje pro platbu, převod selhal.</p>
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
