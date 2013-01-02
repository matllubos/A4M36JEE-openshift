package cz.cvut.fel.beans;

import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/** @author Karel Cemus */
@Data
@Named
@RequestScoped
@Deprecated
public class DispatcherBean implements Serializable {

    //    @Inject
    //    private FacadeService service;

    @Inject
    private SessionBean session;

    private long reservationId;

    private String reservationPassword;

    public String findReservation() {
        //        try {
        //            service.findReservation( reservationId, reservationPassword );
        //            session.setIdentifier( reservationId );
        //            session.setPassword( reservationPassword );
        //            return "reservation";
        //        } catch ( SecurityException e ) {
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Heslo pro přístup k rezervaci není správné" ) );
        return null;
        //        }
    }
}
