package cz.cvut.fel.aos.bookingserver.service.destination;

import cz.cvut.fel.aos.bookingserver.model.Destination;
import java.util.Collection;
import javax.jws.WebService;

/**
 * API poskytující přístup k možným destinacím letů
 *
 * @author Karel Cemus
 */
@WebService
public interface DestinationService {

    /** Vrací všechny destinace, do kterých společnost létá */
    Collection<Destination> findAll();
}
