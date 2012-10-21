package cz.cvut.fel.aos.bookingserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Model zachycuje všechny lety, které je v systému možné najít a rezervovat.
 *
 * @author Karel Cemus
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@NamedQueries( {
        @NamedQuery( name = "Flight.findByNumber", query = "SELECT f FROM Flight f WHERE f.number = :number" ),
        @NamedQuery( name = "Flight.findAll", query = "SELECT f FROM Flight f" ),
        @NamedQuery( name = "Flight.findByFrom", query = "SELECT f FROM Flight f WHERE f.departure >= :intervalFrom AND f.departure <= :intervalTo AND f.to = :codeTo" ),
        @NamedQuery( name = "Flight.findByTo", query = "SELECT f FROM Flight f WHERE f.departure >= :intervalFrom AND f.departure <= :intervalTo AND f.from = :codeFrom" ),
        @NamedQuery( name = "Flight.findByFromTo", query = "SELECT f FROM Flight f WHERE f.departure >= :intervalFrom AND f.departure <= :intervalTo AND f.from = :codeFrom AND f.to = :codeTo" )
} )
public class Flight implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** číslo letu */
    @Column( length = 20, nullable = false )
    private String number;

    /** datum a čas odletu */
    @Column( nullable = false )
    @Temporal( javax.persistence.TemporalType.TIMESTAMP )
    private Date departure;

    /** datum a čas příletu */
    @Column( nullable = false )
    @Temporal( javax.persistence.TemporalType.TIMESTAMP )
    private Date arrival;

    /** cena letu */
    @Column( nullable = false )
    private int cost;

    /** odletá z oblasti */
    @ManyToOne( optional = false )
    @JoinColumn( name = "DESTINATION_FROM" )
    private Destination from;

    /** letí do oblasti */
    @ManyToOne( optional = false )
    @JoinColumn( name = "DESTINATION_TO" )
    private Destination to;

    /** celková kapacita letu */
    private int capacity;

    /** zbývající volná kapacity */
    private int capacityLeft;

    /** optimistický zámek */
    @Version
    private long version;

}
