package cz.cvut.fel.aos.beans;

import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

import static cz.cvut.fel.aos.utils.DateUtils.date;

/** @author Karel Cemus */
@Data
@Component( "context" )
@Scope( "session" )
public class SessionBean {

    @Autowired
    private FileBean file;

    /** flight-filter-configuration */
    private Date dateFrom;

    /** flight-filter-configuration */
    private Date dateTo;

    /** flight-filter-configuration */
    private String destinationFrom = "PRG";

    /** flight-filter-configuration */
    private String destinationTo;

    /** reservation view configuration */
    private long identifier;

    /** reservation view configuration */
    @Setter
    private String password;

    public SessionBean() {
        dateFrom = date( 1, 1, 2012 );

        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.DATE, 14 );
        dateTo = calendar.getTime();
    }

    public void setIdentifier( long identifier ) {
        // reset files, was attached to another reservation
        file.reset();
        this.identifier = identifier;
    }

    public String home() {
        this.identifier = 0;
        this.password = null;
        file.reset();
        return "home";
    }
}
