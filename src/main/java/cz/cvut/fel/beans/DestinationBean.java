package cz.cvut.fel.beans;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.service.DestinationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Collections;

/** @author Karel Cemus */
@Named( "destinations" )
@ApplicationScoped
public class DestinationBean {

    @Inject
    private DestinationService service;

    /** data are old, has to be reloaded */
    private boolean dirtyData = true;

    /** cached destinations, static content, rarely changed */
    private Collection<Destination> destinations = Collections.emptyList();

    public Collection<Destination> getDestinations() {
        if ( dirtyData ) {
            // preserve order due to potential race conditions
            // better to load twice same data than miss change notification
            dirtyData = false;
            destinations = service.findAllDestinations();
        }
        return destinations;
    }

    public void update() {
        dirtyData = true;
    }
}
