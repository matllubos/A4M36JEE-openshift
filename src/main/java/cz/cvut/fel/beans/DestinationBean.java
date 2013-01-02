package cz.cvut.fel.beans;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.service.DestinationService;
import lombok.Getter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;

/** @author Karel Cemus */
@ViewScoped
@ManagedBean( name = "destination" )
public class DestinationBean extends BeanBase implements Converter {

    @Inject
    private DestinationService service;

    @Inject
    private DestinationsBean destinations;

    @Getter
    private Destination destination = new Destination();

    public String save() {
        try {
            // persist the destination
            service.save( destination );

            // log it
            addInformation( "Destination successfully created." );

            // reload all destinations
            destinations.update();

            return "destinations";

        } catch ( Throwable ex ) {
            while ( ex.getCause() != null ) {
                ex = ex.getCause();
            }
            addError( ex.getMessage() );
            return null;
        }
    }


    public void setDestination( final Destination destination ) {
        this.destination = destination;
    }

    @Override
    public Object getAsObject( final FacesContext context, final UIComponent component, final String value ) {
        try {
            if ( value == null ) return new Destination();
            return service.findByCode( value );
        } catch ( Throwable ex ) {
            while ( ex.getCause() != null ) {
                ex = ex.getCause();
            }
            throw new ConverterException( String.format( ex.getMessage() ) );
        }
    }

    @Override
    public String getAsString( final FacesContext context, final UIComponent component, final Object value ) {
        if ( value == null ) {
            return null;
        } else {
            return ( ( Destination ) value ).getCode();
        }
    }
}
