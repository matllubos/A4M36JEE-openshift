package cz.cvut.fel.service.destination;

import cz.cvut.fel.model.Destination;

import java.util.Collection;

/**
 * API poskytující přístup k možným destinacím letů
 *
 * @author Karel Cemus
 */
public interface DestinationService {

    /** Vrací všechny destinace, do kterých společnost létá */
    Collection<Destination> findAllDestinations();
}
