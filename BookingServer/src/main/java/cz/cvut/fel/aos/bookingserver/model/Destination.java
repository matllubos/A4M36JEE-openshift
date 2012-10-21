package cz.cvut.fel.aos.bookingserver.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @NamedQuery( name = "Destination.findAll", query = "SELECT d FROM Destination d" )
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

}
