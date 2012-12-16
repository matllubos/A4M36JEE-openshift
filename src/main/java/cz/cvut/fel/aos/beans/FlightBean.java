package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.model.Flight;
import cz.cvut.fel.aos.service.facade.FacadeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Collection;
import java.util.Collections;

/** @author Karel Cemus */
@Data
@Component
@Scope( "request" )
public class FlightBean {

    @Autowired
    private FacadeService service;

    @Autowired
    private SessionBean session;

    private Collection<Flight> flights;

    public Collection<Flight> getFlights() {
        if ( flights == null ) {
            flights = filter();
        }
        return flights;
    }

    public Collection<Flight> filter() {
        if ( session.getDestinationFrom() == null && session.getDestinationTo() == null ) {
            FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( "Musí být vybrána alespoň jedna destinace (přílet / odlet)." ) );
            return Collections.emptyList();
        }

        if ( session.getDestinationFrom() == null ) {
            return service.findFlightsTo( session.getDateFrom(), session.getDateTo(), session.getDestinationTo() );
        } else if ( session.getDestinationTo() == null ) {
            return service.findFlightsFrom( session.getDateFrom(), session.getDateTo(), session.getDestinationFrom() );
        } else {
            return service.findFlights( session.getDateFrom(), session.getDateTo(), session.getDestinationFrom(), session.getDestinationTo() );
        }
    }
}
