package cz.cvut.fel.aos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Model reflects all flights which is possible to find and book in the system.
 *
 * @author Karel Cemus
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@NamedQueries( {

        /** For administration purposes only */
        @NamedQuery( name = "Flight.findAll", query = "SELECT f FROM Flight f" ),

        /** finds the flight by its number */
        @NamedQuery( name = "Flight.findByNumber", query = "SELECT f FROM Flight f WHERE f.number = :number" ),

        /** finds all scheduled but not realised flights from the location. */
        @NamedQuery( name = "Flight.findByFrom", query = "SELECT f FROM Flight f WHERE " +
                "f.departure.actual >= :intervalFrom AND " +
                "f.departure.actual <= :intervalTo AND " +
                "f.from.code = :codeFrom AND " +
                "f.status <> 'CANCELED' AND " +
                "f.status <> 'LANDED'" ),

        /** finds all scheduled but not realised flights to the location. */
        @NamedQuery( name = "Flight.findByTo", query = "SELECT f FROM Flight f WHERE " +
                "f.departure.actual >= :intervalFrom AND " +
                "f.departure.actual <= :intervalTo AND " +
                "f.to.code = :codeTo AND " +
                "f.status <> 'CANCELED' AND " +
                "f.status <> 'LANDED'" ),

        /** finds all scheduled but not realised flights between the locations. */
        @NamedQuery( name = "Flight.findByFromTo", query = "SELECT f FROM Flight f WHERE " +
                "f.departure.actual >= :intervalFrom AND " +
                "f.departure.actual <= :intervalTo AND " +
                "f.from.code = :codeFrom AND " +
                "f.to.code = :codeTo AND " +
                "f.status <> 'CANCELED' AND " +
                "f.status <> 'LANDED'" )
} )
public class Flight implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** flight number */
    @Column( length = 20, nullable = false )
    private String number;

    /** departure date and time */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="actual",column=@Column(name="DEPARTURE_ACTUAL")),
            @AttributeOverride(name="scheduled",column=@Column(name="DEPARTURE_SCHEDULED"))
    })
    private Time departure;

    /** arrival date and time */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="actual",column=@Column(name="ARRIVAL_ACTUAL")),
            @AttributeOverride(name="scheduled",column=@Column(name="ARRIVAL_SCHEDULED"))
    })
    private Time arrival;

    /** flight price per seat */
    @Column( nullable = false )
    private int cost;

    /** departure destination */
    @ManyToOne( optional = false )
    @JoinColumn( name = "DEPARTURE_FROM" )
    private Destination from;

    /** arrival destination */
    @ManyToOne( optional = false )
    @JoinColumn( name = "ARRIVAL_TO" )
    private Destination to;

    /** total plane capacity */
    private int capacity;

    /** capacity left - number of free seats */
    private int capacityLeft;

    /** current status of flight */
    @NotNull
    @Enumerated( EnumType.STRING )
    private FlightStatus status;

    /** optimistic lock */
    @Version
    private long version;

}
