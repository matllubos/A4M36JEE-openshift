package cz.cvut.fel.aos.service.adapter;

import javax.xml.ws.WebFault;

/** @author Karel Cemus */
@WebFault( name = "FullFlightFault" )
public class FullFlightException extends Exception {

    public FullFlightException() {
    }

    public FullFlightException( final String message ) {
        super( message );
    }

    public FullFlightException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}

