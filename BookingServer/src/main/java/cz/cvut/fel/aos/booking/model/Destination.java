package cz.cvut.fel.aos.booking.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Oblast ze které či do které se uskutečňují lety.
 *
 * @author Karel Cemus
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@NamedQueries( {
        @NamedQuery( name = "Destination.findAll", query = "SELECT d FROM Destination d" ),
        @NamedQuery( name = "Destination.findByCode", query = "SELECT d FROM Destination d WHERE d.code = :code" )
} )
public class Destination implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** kód destinace */
    @Column( unique = true )
    private String code;

    /** název destinace */
    private String name;

    /** optimistický zámek */
    @Version
    private long version;

}
