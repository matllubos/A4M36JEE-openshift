package cz.cvut.fel.aos.payment.service.payment;

import javax.xml.ws.WebFault;

/**
 * <p>Chyba indikující neoprávněný přístup</p>
 *
 * @author Karel Cemus
 */
@WebFault( name = "SecurityFault" )
public class SecurityException extends RuntimeException {

    public SecurityException() {
    }

    public SecurityException( final String message ) {
        super( message );
    }

    public SecurityException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
