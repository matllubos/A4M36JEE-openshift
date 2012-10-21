package cz.cvut.fel.aos.bookingserver.service.impl;

import cz.cvut.fel.aos.bookingserver.model.Destination;
import cz.cvut.fel.aos.bookingserver.service.DestinationService;
import java.util.Collection;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Karel Cemus
 */
@WebService( endpointInterface = "cz.cvut.fel.aos.bookingserver.service.DestinationService" )
public class DestinationServiceImpl implements DestinationService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Destination> findAll() {
        return em.createNamedQuery( "Destination.findAll", Destination.class ).getResultList();
    }
}
