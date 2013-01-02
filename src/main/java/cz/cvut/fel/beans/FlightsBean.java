package cz.cvut.fel.beans;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.service.FlightService;
import cz.cvut.fel.utils.DateUtils;
import lombok.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;

/** @author Karel Cemus */
@Data
@Named( "flights" )
@RequestScoped
@EqualsAndHashCode( callSuper = false )
public class FlightsBean extends BeanBase {

    @Getter
    private static final TimeZone TIMEZONE = TimeZone.getDefault();

    @Inject
    @Getter( AccessLevel.NONE )
    @Setter( AccessLevel.NONE )
    private FlightService service;

    // in default look at today
    @NotNull
    private Date dateFrom = DateUtils.date( 1, 1, 2012 );

    // initialize default look 1 month forward
    @NotNull
    private Date dateTo = DateUtils.addMonths( dateFrom, 1 );

    private String departureFrom;

    private String arrivalTo;

    @Setter( AccessLevel.NONE )
    private Collection<Flight> flights;

    public Collection<Flight> getFlights() {
        if ( flights == null ) {
            flights = filter();
        }
        return flights;
    }

    @AssertTrue( message = "At least one destination has too be set" )
    public boolean isDestinationSet() {
        return ( departureFrom != null && !departureFrom.isEmpty() ) || ( arrivalTo != null && !arrivalTo.isEmpty() );
    }

    public Collection<Flight> filter() {

        // neither destination is set
        if ( departureFrom == null && arrivalTo == null ) {
            return Collections.emptyList();
        }

        if ( departureFrom == null || departureFrom.isEmpty() ) {
            return service.findFlightsTo( dateFrom, dateTo, arrivalTo );
        } else if ( arrivalTo == null || arrivalTo.isEmpty() ) {
            return service.findFlightsFrom( dateFrom, dateTo, departureFrom );
        } else {
            return service.findFlights( dateFrom, dateTo, departureFrom, arrivalTo );
        }
    }

    public String remove( String number ) {
        try {
            service.delete( number );
            addInformation( "Flight was removed." );
        } catch ( Throwable ex ) {
            addError( "No such flight exists." );
        }

        return "flights";
    }
}
