package cz.cvut.fel.aos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Collection;

/**
 * Model representing reservations in the booking system. User is able to book certain number of seats on selected flight.
 *
 * @author Karel Cemus
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@NamedQueries( {
        /** for administration purpose only */
        @NamedQuery( name = "Reservation.findAll", query = "SELECT r FROM Reservation r" ),

        /** lists all reservations for given flight */
        @NamedQuery( name = "Reservation.findAllForFlight", query = "SELECT r FROM Reservation r WHERE r.flight.number = :number" )
} )
public class Reservation implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** access password */
    @NotBlank
    @Length( min = 8 )
    private String password;

    @ManyToOne( optional = false )
    private Flight flight;

    /** amount of booked seats */
    @Min( 1 )
    private int count;

    /** reservation price, redundant information to allow flight price change in time */
    @Min( 0 )
    private int cost;

    /** paid money to this reservations */
    @Min( 0 )
    private int paid;

    /** reservation status */
    private boolean canceled;

    /** optimistic lock */
    @Version
    private long version;

    @OneToMany( mappedBy = "reservation", fetch = FetchType.LAZY )
    private Collection<Payment> payments;
}
