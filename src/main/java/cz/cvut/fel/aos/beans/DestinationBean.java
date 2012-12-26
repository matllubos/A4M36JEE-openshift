package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.model.Destination;
import cz.cvut.fel.aos.service.facade.FacadeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;

/** @author Karel Cemus */
@Named( "destinationBean" )
@RequestScoped
public class DestinationBean {

    @Inject
    private FacadeService service;

    private Collection<Destination> destinations;

    public Collection<Destination> getDestinations() {
        System.out.println( "aaaaaaaaa" );
        if ( destinations == null ) {
            System.out.println( "yyyyyyyyyy" );
            destinations = service.findAllDestinations();
        }
        return destinations;
    }
}
