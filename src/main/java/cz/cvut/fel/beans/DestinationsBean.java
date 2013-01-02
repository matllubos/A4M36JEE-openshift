package cz.cvut.fel.beans;

import cz.cvut.fel.exception.NoSuchDestinationException;
import cz.cvut.fel.model.Destination;
import cz.cvut.fel.service.DestinationService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** @author Karel Cemus */
@Named( "destinations" )
@ApplicationScoped
public class DestinationsBean extends BeanBase {


    @Inject
    private DestinationService service;

    /** data are old, has to be reloaded */
    private boolean dirtyData = true;

    /** cached destinations, static content, rarely changed */
    private Collection<Destination> destinations = Collections.emptyList();

    /** translation map */
    private Map<String, Destination> mapping = Collections.emptyMap();

    public Collection<Destination> getDestinations() {
        if ( dirtyData ) reload();
        return destinations;
    }

    public void update() {
        dirtyData = true;
    }

    public String remove( long id ) {

        try {
            service.delete( id );
            dirtyData = true;
            addInformation( "Destination was removed." );
        } catch ( Throwable ex ) {
            addError( "No such destination exists." );
        }

        return "destinations";
    }

    public Destination translate( String code ) {
        if ( dirtyData ) reload();
        return mapping.get( code );
    }

    private void reload() {
        // preserve order due to potential race conditions
        // better to load twice same data than miss change notification
        dirtyData = false;

        // load new collection and create mapping
        Collection<Destination> destinations = service.findAllDestinations();
        Map<String, Destination> mapping = new HashMap<String, cz.cvut.fel.model.Destination>();
        for ( Destination destination : destinations ) {
            mapping.put( destination.getCode(), destination );
        }

        // switch mapping and collection
        this.destinations = destinations;
        this.mapping = mapping;
    }


//    @Override
//    public Object getAsObject( final FacesContext context, final UIComponent component, final String value ) {
//        if ( value == null ) return new Destination();
//        Destination destination = translate( value );
//        if ( destination == null ) {
//            throw new NoSuchDestinationException( String.format( "No destination with code '%1$s' exists.", value ) );
//        }
//        return destination;
//        return null;
//    }

//    @Override
//    public String getAsString( final FacesContext context, final UIComponent component, final Object value ) {
//        return "XXXX";
//        if ( value == null ) {
//            return null;
//        } else if ( value instanceof String ) {
//            return value.toString();
//        } else {
//            return ( ( Destination ) value ).getCode();
//        }
//    }
}
