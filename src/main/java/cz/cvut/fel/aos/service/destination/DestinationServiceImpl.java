package cz.cvut.fel.aos.service.destination;

import cz.cvut.fel.aos.model.Destination;
import lombok.extern.slf4j.Slf4j;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/** @author Karel Cemus */
@Slf4j
@WebService( endpointInterface = "cz.cvut.fel.aos.service.destination.DestinationService" )
public class DestinationServiceImpl implements DestinationService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Destination> findAllDestinations() {
        return em.createNamedQuery( "Destination.findAll", Destination.class ).getResultList();
    }
}
