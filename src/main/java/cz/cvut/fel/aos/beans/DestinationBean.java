package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.model.Destination;
import cz.cvut.fel.aos.service.facade.FacadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/** @author Karel Cemus */
@Component
@Scope( "request" )
public class DestinationBean {

    @Autowired
    private FacadeService service;

    private Collection<Destination> destinations;

    public Collection<Destination> getDestinations() {
        if ( destinations == null ) {
            destinations = service.findAllDestinations();
        }
        return destinations;
    }
}
