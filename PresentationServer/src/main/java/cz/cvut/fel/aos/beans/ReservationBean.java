package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.InterfaceService;
import cz.cvut.fel.aos.service.facade.*;
import cz.cvut.fel.aos.service.facade.SecurityException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
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

    private String fileTitle;

    private String fileName;

    private int fileLength;

    private byte[] fileContent;

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
            fileContent = success.getConfirmation();
            fileName = "confirmation.txt";
            fileTitle = "Stáhnout potvrzení o rezervaci. OBSAHUJE HESLO pro přístup.";
            fileLength = fileContent.length;
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
        response.setContentType( "plain/text; charset=utf-8" );
        // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.
        response.setHeader( "Content-disposition", "attachment; filename=\"" + fileName + "\"" );

        BufferedOutputStream output = null;

        try {
            output = new BufferedOutputStream( response.getOutputStream() );

            byte[] buffer = new byte[ 10240 ];
            output.write( fileContent, 0, fileLength );
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
        fileContent = null;
        fileTitle = null;
        fileName = null;
    }

    public String cancel() {
        try {
            DataHandler handler = service.cancelReservation( identifier, password );

            fileContent = new byte[ 1024 * 100 ];
            fileLength = handler.getInputStream().read( fileContent );
            fileName = "cancel-confirmation.txt";
            fileTitle = "Stáhnout potvrzení o zrušení rezervace";

            find();

        } catch ( NoSuchReservationException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace nebyla nalezena." ) );
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Přístup k rezervaci nebyl povolen. Zkontrolujte heslo a opakujte akci později." ) );
        } catch ( IOException e ) {
            log.warn( "Stažení souboru eticket se nepovedlo." );
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Příprava souboru se nezdařila, opakujte prosím akci později." ) );
        }
        return "reservation";
    }

    public void printETicket() {
        try {
            DataHandler handler = service.printTicket( identifier, password );

            fileContent = new byte[ 1024 * 100 ];
            fileLength = handler.getInputStream().read( fileContent );
            fileName = "eticket.txt";
            fileTitle = "Stáhnout E-letenku";
        } catch ( NoSuchReservationException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace nebyla nalezena." ) );
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Přístup k rezervaci nebyl povolen. Zkontrolujte heslo a opakujte akci později." ) );
        } catch ( ReservationNotPaidException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace ještě není zaplacena, nelze vytisknout platnou letenku." ) );
        } catch ( IOException e ) {
            log.warn( "Stažení souboru eticket se nepovedlo." );
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Příprava souboru se nezdařila, opakujte prosím akci později." ) );
        }
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
