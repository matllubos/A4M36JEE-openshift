package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.model.Flight;
import cz.cvut.fel.aos.service.facade.FacadeService;
import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/** @author Karel Cemus */
@Data
@Named
@RequestScoped
public class FlightBean implements Serializable {

    @Inject
    private FacadeService service;

    @Inject
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
