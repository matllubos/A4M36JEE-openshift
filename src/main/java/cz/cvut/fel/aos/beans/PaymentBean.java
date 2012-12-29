package cz.cvut.fel.aos.beans;

import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

/** @author Karel Cemus */
@Data
@Named
@RequestScoped
public class PaymentBean implements Serializable {

    //    @Inject
    //    private FacadeService service;

    @Inject
    private SessionBean session;

    @Inject
    private FileBean file;

    private long canceledReservationId;

    private String canceledReservationPassword;

    private String cardName;

    private long creditCard;

    private Date validUntil;

    private int verificationCode;


    public String payCanceled() {
        //        try {
        //            service.payFromCanceledReservation( canceledReservationId, canceledReservationPassword, session.getIdentifier(), session.getPassword() );
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Transakce byla úspěšně provedena." ) );
        //
        //            return "reservation";
        //
        //        } catch ( NoSuchReservationException e ) {
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace nebyla nalezena." ) );
        //        } catch ( SecurityException e ) {
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Přístup k rezervaci nebyl povolen. Zkontrolujte heslo a opakujte akci později." ) );
        //        } catch ( InvalidPaymentException e ) {
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Platba nebyla provedena. Chybová zpráva: '" + e.getMessage() + "'." ) );
        //        }
        return null;
    }

    public String payVisa() {
        //        try {
        //            DataHandler handler = service.payVisa( session.getIdentifier(), session.getPassword(), cardName, creditCard, validUntil, verificationCode );
        //            file.setFile( "Stáhnout potvrzení o zaplacení", "payment-confirmation.txt", handler );
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Transakce byla úspěšně provedena." ) );
        //
        //            return "reservation";
        //
        //        } catch ( NoSuchReservationException e ) {
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace nebyla nalezena." ) );
        //        } catch ( SecurityException e ) {
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Přístup k rezervaci nebyl povolen. Zkontrolujte heslo a opakujte akci později." ) );
        //        } catch ( InvalidPaymentException e ) {
        //            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Platba nebyla provedena. Chybová zpráva: '" + e.getMessage() + "'." ) );
        //        }
        return null;
    }
}
