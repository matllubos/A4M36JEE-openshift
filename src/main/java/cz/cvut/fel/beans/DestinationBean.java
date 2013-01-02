package cz.cvut.fel.beans;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.service.DestinationService;
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
@ManagedBean( name = "destination" )
public class DestinationBean extends BeanBase implements Converter, Serializable {

    @Inject
    private DestinationService service;

    @Inject
    private DestinationsBean destinations;

    @Getter
    @Setter
    private Destination destination = new Destination();

    public String save() {
        try {
            boolean isNew = destination.getId() == 0;

            // persist the destination
            service.save( destination );

            // log it
            if ( isNew ) {
                addInformation( "Destination successfully created." );
            } else {
                addInformation( "Destination successfully updated." );
            }

            // reload all destinations
            destinations.update();

            return "destinations";

        } catch ( Throwable ex ) {
            addError( processException( ex ) );
            return null;
        }
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
