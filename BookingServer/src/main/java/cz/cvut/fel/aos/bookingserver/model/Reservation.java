package cz.cvut.fel.aos.bookingserver.model;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Model reprezentující rezervace v bookovacím systému. Uživatel může požádat o
 * rezervaci míst na určitý let.
 *
 * @author Karel Cemus
 */
@Data
@Entity
@ToString
@NoArgsConstructor
@NamedQueries( {
    @NamedQuery( name = "Reservation.findAll", query = "SELECT r FROM Reservation r" )
} )
public class Reservation implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;

    /** heslo pro přístup */
    private String password;

    @ManyToOne( optional = false )
    private Flight flight;

    /** počet rezervovaných míst */
    private int count;

    /** cena rezervace, redundantní, aby mohlo dojít ke změně ceny letu s blížícím se
     * termínem odletu, což je běžná praxe */
    private int cost;

    /** zaplaceno, částka zaplacená k této rezervaci, Lze použít při placení jiných
     * rezervací */
    private int paid;

    /** rezervace byla zrušena */
    private boolean canceled;

    /** optimistický zámek */
    @Version
    private long version;

}
