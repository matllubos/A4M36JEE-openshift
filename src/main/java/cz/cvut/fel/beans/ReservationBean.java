package cz.cvut.fel.beans;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.Reservation;
import cz.cvut.fel.service.ReservationService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import java.io.Serializable;

/** @author Karel Cemus */
@Setter
@SessionScoped
@Named( "reservationBean" )
public class ReservationBean extends BeanBase implements Serializable {

    @Inject
    @Setter( AccessLevel.NONE )
    private ReservationService service;

    @Getter
    @Min( value = 1, message = "Invalid identifier. It must be at least 1." )
    private long id;

    @Getter
    private Reservation reservation;

    @Getter
    private Flight flight;

    @Getter
    @NotBlank( message = "Password is missing." )
    private String password;

    public String cancel() {

        try {
            reservation = service.cancel( id, password );
            addInformation( "Reservation was cancelled." );
        } catch ( Throwable ex ) {
            addError( processException( ex ) );
        }

        return "reservation?includeViewParams=true&faces-redirect=true";
    }

    public void printETicket() {

        try {
            byte[] ticket = service.printETicket( id, password );
            download( "e-ticket.txt", ticket.length, ticket );
        } catch ( Throwable ex ) {
            addError( processException( ex ) );
        }
    }

    public void printConfirmation() {
        try {
            byte[] confirmation;
            if ( reservation.isCancelled() ) {
                confirmation = service.printCancelConfirmation( id, password );
            } else {
                confirmation = service.printReservationConfirmation( id, password );
            }
            download( "confirmation.txt", confirmation.length, confirmation );
        } catch ( Throwable ex ) {
            addError( processException( ex ) );
        }
    }

    public String logIn() {

        try {

            if ( id > 0 && password != null ) {

                // verify login information
                reservation = service.find( id, password );
                flight = reservation.getFlight();
            }

        } catch ( Throwable ex ) {

            // verification failed
            addError( processException( ex ) );
        }

        return "reservation?includeViewParams=true&faces-redirect=true";
    }

    public String getReservationStatus() {
        if ( reservation.isCancelled() ) {
            return "Cancelled";
        } else if ( reservation.isFullyPaid() ) {
            return "Paid";
        } else {
            return "Active";
        }
    }
}
