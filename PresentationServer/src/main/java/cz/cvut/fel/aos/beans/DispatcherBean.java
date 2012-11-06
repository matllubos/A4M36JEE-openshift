package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.InterfaceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/** @author Karel Cemus */
@Data
@Component
@Scope( "request" )
public class DispatcherBean {

    @Autowired
    private InterfaceService service;

    @Autowired
    private SessionBean session;

    private long reservationId;

    private String reservationPassword;

    public String findReservation() {
        try {
            service.findReservation( reservationId, reservationPassword );
            session.setIdentifier( reservationId );
            session.setPassword( reservationPassword );
            return "reservation";
        } catch ( cz.cvut.fel.aos.service.facade.SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Heslo pro přístup k rezervaci není správné" ) );
            return null;
        }
    }
}
