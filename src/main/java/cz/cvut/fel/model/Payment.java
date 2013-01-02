package cz.cvut.fel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * <p>Payment model for realised money transactions.</p>
 *
 * @author Karel Cemus
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode( of = "id" )
@NamedQueries( {
        /** for administration purpose only */
        @NamedQuery( name = "Payment.findAll", query = "SELECT p FROM Payment p" )
} )
public class Payment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** credit card number */
    private long creditCardNumber;

    @NotNull
    /** card owner's name */
    private String creditCardName;

    /** money transferred */
    private long amount;

    /** paid reservation */
    @ManyToOne( optional = false )
    private Reservation reservation;

    @NotNull
    @Temporal( TemporalType.TIMESTAMP )
    private Date timestamp;
}
