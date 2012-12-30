package cz.cvut.fel.service.destination;

import cz.cvut.fel.model.Destination;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/** @author Karel Cemus */
@Slf4j
@Stateless
public class DestinationServiceImpl implements DestinationService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Collection<Destination> findAllDestinations() {
        return em.createNamedQuery( "Destination.findAll", Destination.class ).getResultList();
    }
}
