package cz.cvut.fel.service;

import cz.cvut.fel.exception.NoSuchFlightException;
import cz.cvut.fel.model.Flight;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Date;

/** @author Karel Cemus */
@Slf4j
@Stateless
public class FlightServiceImpl implements FlightService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Flight find( String flightNumber ) {

        // verify flightNumber is set
        if ( flightNumber == null || flightNumber.isEmpty() ) {
            throw new IllegalArgumentException( "FlightNumber is required parameter." );
        }

        // try to find active entity with the flightNumber
        try {
            return em.createNamedQuery( "Flight.findByNumber", Flight.class ).setParameter( "number", flightNumber ).getSingleResult();
        } catch ( javax.persistence.NoResultException ignored ) {
            throw new NoSuchFlightException( String.format( "Flight with number '%s' doesn't exist.", flightNumber ) );
        }
    }

    @Override
    public Collection<Flight> findFlightsFrom( Date intervalFrom, Date intervalTo, String codeFrom ) {
        return em.createNamedQuery( "Flight.findByFrom", Flight.class ).
                setParameter( "intervalFrom", intervalFrom ).
                setParameter( "intervalTo", intervalTo ).
                setParameter( "codeFrom", codeFrom ).
                getResultList();
    }

    @Override
    public Collection<Flight> findFlightsTo( Date intervalFrom, Date intervalTo, String codeTo ) {
        return em.createNamedQuery( "Flight.findByTo", Flight.class ).
                setParameter( "intervalFrom", intervalFrom ).
                setParameter( "intervalTo", intervalTo ).
                setParameter( "codeTo", codeTo ).
                getResultList();
    }

    @Override
    public Collection<Flight> findFlights( Date intervalFrom, Date intervalTo, String codeFrom, String codeTo ) {
        return em.createNamedQuery( "Flight.findByFromTo", Flight.class ).
                setParameter( "intervalFrom", intervalFrom ).
                setParameter( "intervalTo", intervalTo ).
                setParameter( "codeFrom", codeFrom ).
                setParameter( "codeTo", codeTo ).
                getResultList();
    }

    @Override
    public Flight save( @Valid final Flight flight ) {

        // verify and validate entity
        if ( flight == null ) throw new IllegalArgumentException( "Flight is required parameter." );

        // persist or update
        return em.merge( flight );
    }

    @Override
    public void delete( final String number ) throws NoSuchFlightException {

        if ( number == null || number.isEmpty() ) throw new IllegalArgumentException( "Illegal flight number." );

        int result = em.createNamedQuery( "Flight.invalidate" ).setParameter( "number", number ).executeUpdate();

        if ( result == 0 ) {
            throw new NoSuchFlightException( String.format( "Flight with number '%s' doesn't exist.", number ) );
        } else if ( result > 1 ) {
            log.error( "Flight deletion affected too many rows. Transaction will be rollback." );
            throw new IllegalStateException( "Flight deletion affected too many rows." );
        }
    }
}
