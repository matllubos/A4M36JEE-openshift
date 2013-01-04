package cz.cvut.fel.beans;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.service.DestinationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Collections;

/** @author Karel Cemus */
@RequestScoped
@Named( "destinations" )
public class DestinationsBean extends BeanBase {

    @Inject
    private DestinationService service;

    /** data are old, has to be reloaded */
    private boolean dirtyData = true;

    /** cached destinations, static content, rarely changed */
    private Collection<Destination> destinations = Collections.emptyList();

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

    private void reload() {
        // preserve order due to potential race conditions
        // better to load twice same data than miss change notification
        dirtyData = false;

        // load new collection and create mapping
        destinations = service.findAllDestinations();
    }
}
