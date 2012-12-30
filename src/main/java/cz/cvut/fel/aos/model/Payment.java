package cz.cvut.fel.aos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@NamedQueries( {
        /** for administration purpose only */
        @NamedQuery( name = "Payment.findAll", query = "SELECT p FROM Payment p" )
} )
public class Payment {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** credit card number */
    @Min( 100000000000L )
    @Max( 999999999999L )
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
