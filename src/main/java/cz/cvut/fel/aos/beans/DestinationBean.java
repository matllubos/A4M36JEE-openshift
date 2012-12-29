package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.model.Destination;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Collection;

/** @author Karel Cemus */
@Named
@RequestScoped
public class DestinationBean {

    //    @Inject
    //    private FacadeService service;

    private Collection<Destination> destinations;

    public Collection<Destination> getDestinations() {
        if ( destinations == null ) {
            //            destinations = service.findAllDestinations();
        }
        return destinations;
    }
}
