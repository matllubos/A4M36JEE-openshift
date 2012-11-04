package cz.cvut.fel.aos.service.adapter;

import javax.xml.ws.WebFault;

/** @author Karel Cemus */
@WebFault( name = "SecurityFault" )
public class SecurityException extends Exception {

    public SecurityException() {
    }

    public SecurityException( final String message ) {
        super( message );
    }

    public SecurityException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
