package cz.cvut.fel.aos.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Location from which or to which planes fly
 *
 * @author Karel Cemus
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@Table( uniqueConstraints = @UniqueConstraint( columnNames = { "code", "validUntil" } ) )
@NamedQueries( {
        /** for administration purpose only */
        @NamedQuery( name = "Destination.findAll", query = "SELECT d FROM Destination d" ),

        /** lists all active destinations */
        @NamedQuery( name = "Destination.findAllValid", query = "SELECT d FROM Destination d WHERE d.validUntil IS NULL" ),

        /** finds the destination by its code */
        @NamedQuery( name = "Destination.findByCode", query = "SELECT d FROM Destination d WHERE d.code = :code" )
} )

public class Destination implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** destination code */
    @Length( min = 3, max = 3 )
    private String code;

    /** destination name */
    @NotBlank
    private String name;

    /** instance is valid until deletion date */
    @Temporal( TemporalType.TIMESTAMP )
    private Date validUntil;

    /** optimistic lock */
    @Version
    private long version;
}
