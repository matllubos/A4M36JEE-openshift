package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.facade.FacadeService;
import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/** @author Karel Cemus */
@Data
@Named
@RequestScoped
public class DispatcherBean implements Serializable {

    @Inject
    private FacadeService service;

    @Inject
    private SessionBean session;

    private long reservationId;

    private String reservationPassword;

    public String findReservation() {
        try {
            service.findReservation( reservationId, reservationPassword );
            session.setIdentifier( reservationId );
            session.setPassword( reservationPassword );
            return "reservation";
        } catch ( cz.cvut.fel.aos.service.payment.SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Heslo pro přístup k rezervaci není správné" ) );
            return null;
        }
    }
}
