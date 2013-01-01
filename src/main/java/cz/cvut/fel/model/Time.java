package cz.cvut.fel.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Embeddable entity representing planned and actual departure or arrival time.</p>
 *
 * @author Karel Cemus
 */
@Data
@ToString
@Embeddable
@NoArgsConstructor
public class Time implements Serializable {

    /** scheduled date and time */
    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date scheduled;

    /** actual date and time */
    @Column( nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date actual;

    public Time( final Date scheduled ) {
        this.scheduled = scheduled;
        this.actual = scheduled;
    }
}
