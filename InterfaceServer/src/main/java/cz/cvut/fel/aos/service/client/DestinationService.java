package cz.cvut.fel.aos.service.client;

import cz.cvut.fel.aos.service.adapter.Destination;
import cz.cvut.fel.aos.service.destination.DestinationServiceImplService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import cz.cvut.fel.aos.service.destination.DestinationServiceImplService;

/**
 * <p>Klient pro booking server</p>
 *
 * @author Karel Cemus
 */
@Service
public class DestinationService {

    private final cz.cvut.fel.aos.service.destination.DestinationService client;


    public DestinationService() {
        DestinationServiceImplService factory = new DestinationServiceImplService();
        client = factory.getDestinationServiceImplPort();
    }

    public List<Destination> findAll() {
        List<cz.cvut.fel.aos.service.destination.Destination> destinations = client.findAllDestinations();
        List<Destination> adapted = new ArrayList<Destination>();

        for ( cz.cvut.fel.aos.service.destination.Destination destination : destinations )
            adapted.add( new Destination( destination ) );

        return adapted;
    }


}
