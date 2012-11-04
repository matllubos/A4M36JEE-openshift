package cz.cvut.fel.aos.service.adapter;

import javax.xml.ws.WebFault;

/** @author Karel Cemus */
@WebFault( name = "NoSuchReservationFault" )
public class NoSuchReservationException extends Exception {

    public NoSuchReservationException() {
    }

    public NoSuchReservationException( final String message ) {
        super( message );
    }

    public NoSuchReservationException( final String message, final Throwable cause ) {
        super( message, cause );
    }
}
