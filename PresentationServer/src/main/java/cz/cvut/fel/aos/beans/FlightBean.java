package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.InterfaceService;
import cz.cvut.fel.aos.service.facade.Flight;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static cz.cvut.fel.aos.utils.DateUtils.date;

/** @author Karel Cemus */
@Data
@Component
@Scope( "session" )
public class FlightBean {

    @Autowired
    private InterfaceService service;

    private List<Flight> flights;

    private Date dateFrom;

    private Date dateTo;

    private String destinationFrom;

    private String destinationTo;

    public FlightBean() {
        dateFrom = date( 1, 1, 2012 );

        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.DATE, 14 );
        dateTo = calendar.getTime();

        flights = Collections.emptyList();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void filter() {
        if ( destinationFrom == null && destinationTo == null ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Musí být vybrána alespoň jedna destinace (přílet / odlet)." ) );
            return;
        }

        if ( destinationFrom == null ) {
            flights = service.findFlightsTo( dateFrom, dateTo, destinationTo );
        } else if ( destinationTo == null ) {
            flights = service.findFlightsFrom( dateFrom, dateTo, destinationFrom );
        } else {
            flights = service.findFlights( dateFrom, dateTo, destinationFrom, destinationTo );
        }
    }
}
