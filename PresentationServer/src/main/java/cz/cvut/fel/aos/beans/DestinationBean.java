package cz.cvut.fel.aos.beans;

import cz.cvut.fel.aos.service.InterfaceService;
import cz.cvut.fel.aos.service.facade.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/** @author Karel Cemus */
@Component
@Scope( "request" )
public class DestinationBean {

    @Autowired
    private InterfaceService service;

    private List<Destination> destinations;

    public List<Destination> getDestinations() {
        if ( destinations == null ) {
            destinations = service.findAllDestinations();
        }
        return destinations;
    }
}
