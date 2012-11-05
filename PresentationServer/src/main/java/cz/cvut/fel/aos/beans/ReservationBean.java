package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.InterfaceService;
import cz.cvut.fel.aos.service.facade.Reservation;
import cz.cvut.fel.aos.service.facade.SecurityException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/** @author Karel Cemus */
@Getter
@Component
@Scope( "request" )
public class ReservationBean {

    @Autowired
    private InterfaceService service;

    private Reservation reservation;

    @Setter
    private long identifier;

    @Setter
    private String password;

    public String find() {
        try {
            reservation = service.findReservation( identifier, password );
            return "reservation";
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Heslo pro přístup k rezervaci není správné" ) );
            return null;
        }
    }
}
