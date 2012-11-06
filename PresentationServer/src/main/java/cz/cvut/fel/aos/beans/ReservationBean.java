package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.InterfaceService;
import cz.cvut.fel.aos.service.facade.*;
import cz.cvut.fel.aos.service.facade.SecurityException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/** @author Karel Cemus */
@Slf4j
@Getter
@Component
@Scope( "request" )
public class ReservationBean {

    @Autowired
    private InterfaceService service;

    @Autowired
    private SessionBean session;

    @Autowired
    private FileBean file;

    private Reservation reservation;

    public Reservation getReservation() {
        if ( reservation == null ) {
            reservation = find();
        }
        return reservation;
    }

    public Reservation find() {
        try {
            if ( session.getIdentifier() == 0 ) return null;
            return service.findReservation( session.getIdentifier(), session.getPassword() );
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Heslo pro přístup k rezervaci není správné" ) );
            return null;
        }
    }

    public String createReservation( String flightNumber ) {
        try {
            int count = Integer.parseInt( FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get( flightNumber + "-count" ) );
            session.setPassword( generatePassword() );

            SuccessfulReservation success = service.createReservation( flightNumber, session.getPassword(), count );
            session.setIdentifier( success.getReservation().getId() );
            file.setFile( "Stáhnout potvrzení o rezervaci. OBSAHUJE HESLO pro přístup.", "confirmation.txt", success.getConfirmation() );

            return "reservation";
        } catch ( FullFlightException e ) {
            session.setIdentifier( 0 );
            session.setPassword( null );
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Let je již plně obsazen." ) );
            return null;
        }
    }


    public String cancel() {
        try {
            DataHandler handler = service.cancelReservation( session.getIdentifier(), session.getPassword() );
            file.setFile( "Stáhnout potvrzení o zrušení rezervace", "cancel-confirmation.txt", handler );

        } catch ( NoSuchReservationException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace nebyla nalezena." ) );
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Přístup k rezervaci nebyl povolen. Zkontrolujte heslo a opakujte akci později." ) );
        }
        return "reservation";
    }

    public void printETicket() {
        try {
            DataHandler handler = service.printTicket( session.getIdentifier(), session.getPassword() );
            file.setFile( "Stáhnout E-letenku", "eticket.txt", handler );

        } catch ( NoSuchReservationException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace nebyla nalezena." ) );
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Přístup k rezervaci nebyl povolen. Zkontrolujte heslo a opakujte akci později." ) );
        } catch ( ReservationNotPaidException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace ještě není zaplacena, nelze vytisknout platnou letenku." ) );
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
