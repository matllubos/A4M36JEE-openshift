package cz.cvut.fel.service;

import cz.cvut.fel.model.Destination;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Collection;

/** @author Karel Cemus */
@Slf4j
@Stateless
public class DestinationServiceImpl implements DestinationService {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Destination findByCode( final String code ) throws NoSuchDestinationException {

        // verify code is set
        if ( code == null ) throw new IllegalArgumentException( "Code is required parameter." );

        // try to find active entity with the code
        try {
            return em.createNamedQuery( "Destination.findByCode", Destination.class ).setParameter( "code", code ).getSingleResult();
        } catch ( javax.persistence.NoResultException ignored ) {
            throw new NoSuchDestinationException( String.format( "Destination with code '%s' doesn't exist.", code ) );
        }
    }

    @Override
    public Collection<Destination> findAllDestinations() {

        // return all currently active entities
        return em.createNamedQuery( "Destination.findAllValid", Destination.class ).getResultList();
    }

    @Override
    public Destination saveDestination( @Valid final Destination destination ) {

        // verify and validate entity
        if ( destination == null ) throw new IllegalArgumentException( "Destination is required parameter." );

        // persist or update
        return em.merge( destination );
    }

    @Override
    public void deleteDestination( final long id ) throws NoSuchDestinationException {

        if ( id <= 0 ) throw new IllegalArgumentException( "Illegal identifier. Value must be greater than 0." );

        // try to find the instance
        Destination destination = em.find( Destination.class, id );

        if ( destination == null ) {
            throw new NoSuchDestinationException( String.format( "Destination with id '%d' doesn't exist.", id ) );
        }

        // delete (it is automatically persisted, instance is attached to entity manager)
        destination.invalidate();
    }
}
