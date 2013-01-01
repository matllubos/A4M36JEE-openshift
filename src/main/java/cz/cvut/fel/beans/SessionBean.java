package cz.cvut.fel.beans;

import lombok.Data;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import static cz.cvut.fel.utils.DateUtils.date;

/** @author Karel Cemus */
@Data
@Named( "context" )
@SessionScoped
@Deprecated
public class SessionBean implements Serializable {

    @Inject
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
