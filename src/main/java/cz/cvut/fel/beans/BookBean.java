package cz.cvut.fel.beans;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.Reservation;
import cz.cvut.fel.service.ReservationService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/** @author Karel Cemus */
@Setter
@ViewScoped
@Named( "bookBean" )
public class BookBean extends BeanBase implements Serializable {

    @Inject
    @Setter( AccessLevel.NONE )
    private ReservationService service;

    private Flight flight;

    private int seats;

    private String password;

    @Getter
    private String confirmation;

    public String book() {
        try {

            // make reservation
            Reservation reservation = service.create( flight.getNumber(), password, seats );

            System.out.println( "created " + reservation );

            // log it
            addInformation( "Seats was reserved." );

            return "flights?includeViewParams=true&faces-redirect=true";

        } catch ( Throwable ex ) {
            addError( processException( ex ) );
            return null;
        }
    }

    @AssertTrue( message = "Seats cannot be reserved. Plane capacity would be exceeded." )
    public boolean isCapacityLeft() {
        return seats <= flight.getCapacityLeft();
    }

    @AssertTrue( message = "Passwords are not same." )
    public boolean isPasswordConfirmed() {
        return password != null && password.equals( confirmation );
    }

    @Min( value = 1, message = "You have to ask for at least one seat." )
    public int getSeats() {
        return seats;
    }

    @NotNull
    public Flight getFlight() {
        return flight;
    }

    @NotBlank( message = "Password must be at least 6 characters length." )
    @Length( min = 6, message = "Password must be at least 6 characters length." )
    public String getPassword() {
        return password;
    }
}
