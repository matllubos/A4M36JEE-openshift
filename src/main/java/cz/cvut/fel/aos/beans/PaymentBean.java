package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.facade.FacadeService;
import cz.cvut.fel.aos.service.payment.InvalidPaymentException;
import cz.cvut.fel.aos.service.payment.NoSuchReservationException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Date;

/** @author Karel Cemus */
@Data
@Component
@Scope( "request" )
public class PaymentBean {

    @Autowired
    private FacadeService service;

    @Autowired
    private SessionBean session;

    @Autowired
    private FileBean file;

    private long canceledReservationId;

    private String canceledReservationPassword;

    private String cardName;

    private long creditCard;

    private Date validUntil;

    private int verificationCode;


    public String payCanceled() {
        try {
            service.payFromCanceledReservation( canceledReservationId, canceledReservationPassword, session.getIdentifier(), session.getPassword() );
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Transakce byla úspěšně provedena." ) );

            return "reservation";

        } catch ( NoSuchReservationException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace nebyla nalezena." ) );
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Přístup k rezervaci nebyl povolen. Zkontrolujte heslo a opakujte akci později." ) );
        } catch ( InvalidPaymentException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Platba nebyla provedena. Chybová zpráva: '" + e.getMessage() + "'." ) );
        }
        return null;
    }

    public String payVisa() {
        try {
            DataHandler handler = service.payVisa( session.getIdentifier(), session.getPassword(), cardName, creditCard, validUntil, verificationCode );
            file.setFile( "Stáhnout potvrzení o zaplacení", "payment-confirmation.txt", handler );
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Transakce byla úspěšně provedena." ) );

            return "reservation";

        } catch ( NoSuchReservationException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Rezervace nebyla nalezena." ) );
        } catch ( SecurityException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Přístup k rezervaci nebyl povolen. Zkontrolujte heslo a opakujte akci později." ) );
        } catch ( InvalidPaymentException e ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Platba nebyla provedena. Chybová zpráva: '" + e.getMessage() + "'." ) );
        }
        return null;
    }
}
