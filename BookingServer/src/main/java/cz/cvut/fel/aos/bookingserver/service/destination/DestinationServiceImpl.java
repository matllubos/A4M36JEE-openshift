package cz.cvut.fel.aos.bookingserver.service.destination;

import cz.cvut.fel.aos.bookingserver.model.Destination;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/** @author Karel Cemus */
@WebService( endpointInterface = "cz.cvut.fel.aos.bookingserver.service.destination.DestinationService" )
public class DestinationServiceImpl implements DestinationService {

    @PersistenceContext( unitName = "flightSystemPersistence" )
    private EntityManager em;

    @Override
    public Collection<Destination> findAll() {
        return em.createNamedQuery( "Destination.findAll", Destination.class ).getResultList();
    }
}
