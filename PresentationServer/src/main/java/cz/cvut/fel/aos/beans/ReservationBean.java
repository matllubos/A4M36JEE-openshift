package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.InterfaceService;
import cz.cvut.fel.aos.service.facade.FullFlightException;
import cz.cvut.fel.aos.service.facade.Reservation;
import cz.cvut.fel.aos.service.facade.SecurityException;
import cz.cvut.fel.aos.service.facade.SuccessfulReservation;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;

/** @author Karel Cemus */
@Slf4j
@Getter
@Component
@Scope( "session" )
public class ReservationBean {

    @Autowired
    private InterfaceService service;

    private Reservation reservation;

    private byte[] confirmation;

    @Setter
    private long identifier;

    @Setter
    private String password;

    @Setter
    private int count;

    public String find() {
        try {
            reservation = service.findReservation( identifier, password );
            return "reservation";
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Heslo pro přístup k rezervaci není správné" ) );
            return null;
        }
    }

    public String createReservation( String flightNumber ) {
        try {
            password = generatePassword();
            SuccessfulReservation success = service.createReservation( flightNumber, password, count );
            reservation = success.getReservation();
            identifier = reservation.getId();
            confirmation = success.getConfirmation();
            return "reservation";
        } catch ( FullFlightException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Let je již plně obsazen." ) );
            return null;
        }
    }

    public void download() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = ( HttpServletResponse ) externalContext.getResponse();

        // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        response.reset();
        // Check http://www.w3schools.com/media/media_mimeref.asp for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
        response.setContentType( "plain/text" );
        // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.
        response.setHeader( "Content-disposition", "attachment; filename=\"confirmation.txt\"" );

        BufferedOutputStream output = null;

        try {
            output = new BufferedOutputStream( response.getOutputStream() );

            byte[] buffer = new byte[ 10240 ];
            output.write( confirmation );
        } catch ( IOException e ) {
            log.warn( "File transfer failed.", e );
        } finally {
            if ( output != null ) {
                try {
                    output.close();
                } catch ( IOException ignored ) {
                    //  ignore
                }
            }
        }

        // Important! Else JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
        facesContext.responseComplete();

        // prevent memory leak
        confirmation = null;
    }

    private String generatePassword() {
        StringBuilder builder = new StringBuilder( 10 );
        for ( int i = 0; i < 10; ++i ) {
            int code = ( int ) ( ( Math.random() * 100 ) % 26 );
            builder.append( ( char ) ( 'a' + code ) );
        }
        return builder.toString();
    }
}
