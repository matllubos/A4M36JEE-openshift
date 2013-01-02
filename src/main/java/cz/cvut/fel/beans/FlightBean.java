package cz.cvut.fel.beans;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.FlightStatus;
import cz.cvut.fel.service.FlightService;
import lombok.Getter;
import lombok.Setter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import java.io.Serializable;

/** @author Karel Cemus */
@ViewScoped
@ManagedBean( name = "flight" )
public class FlightBean extends BeanBase implements Converter, Serializable {

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

            return "flights";

        } catch ( Throwable ex ) {
            addError( processException( ex ) );
            return null;
        }
    }

    @Override
    public Object getAsObject( final FacesContext context, final UIComponent component, final String value ) {
        try {
            if ( value == null ) return new Flight();
            return service.find( value );
        } catch ( Throwable ex ) {
            throw new ConverterException( String.format( processException( ex ) ) );
        }
    }

    @Override
    public String getAsString( final FacesContext context, final UIComponent component, final Object value ) {
        if ( value == null ) {
            return null;
        } else {
            return ( ( Flight ) value ).getNumber();
        }
    }

    public FlightStatus[] getStatuses() {
        return FlightStatus.values();
    }
}
