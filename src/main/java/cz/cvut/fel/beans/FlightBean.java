package cz.cvut.fel.beans;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.FlightStatus;
import cz.cvut.fel.service.FlightService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/** @author Karel Cemus */
@ViewScoped
@Named( "flight" )
@EqualsAndHashCode( callSuper = false )
public class FlightBean extends BeanBase implements Serializable {

    @Inject
    private FlightService service;

    @Getter
    @Setter
    private Flight flight = new Flight();

    public String save() {
        try {
            boolean isNew = flight.getId() == 0;

            // persist the flight
            service.save( flight );

            // log it
            if ( isNew ) {
                addInformation( "Flight successfully created." );
            } else {
                addInformation( "Flight successfully updated." );
            }

            return "flights?includeViewParams=true&faces-redirect=true";

        } catch ( Throwable ex ) {
            addError( processException( ex ) );
            return null;
        }
    }

    public FlightStatus[] getStatuses() {
        return FlightStatus.values();
    }
}
