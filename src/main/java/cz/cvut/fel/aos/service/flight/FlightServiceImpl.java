package cz.cvut.fel.aos.service.flight;

import cz.cvut.fel.aos.model.Flight;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return em.createNamedQuery( "Flight.findByNumber", Flight.class ).
                setParameter( "number", flightNumber ).
                getSingleResult();
    }

    @Override
    public Collection<Flight> findAllFlights() {
        return em.createNamedQuery( "Flight.findAll", Flight.class ).
                getResultList();
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
}
