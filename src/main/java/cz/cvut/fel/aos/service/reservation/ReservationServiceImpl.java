package cz.cvut.fel.aos.service.reservation;

import cz.cvut.fel.aos.model.Flight;
import cz.cvut.fel.aos.model.Reservation;
import cz.cvut.fel.aos.utils.SecurityProvider;
import cz.cvut.fel.exception.FullFlightException;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/** @author Karel Cemus */
@Slf4j
@Stateless
public class ReservationServiceImpl implements ReservationService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private SecurityProvider security;

    @Override
    public Reservation find( long reservation, String password ) throws SecurityException {
        Reservation entity = em.find( Reservation.class, reservation );

        // zkontroluj přístup k rezervaci
        security.verifyAccess( entity.getId(), entity.getPassword(), password );

        return entity;
    }

    @Override
    //    @Transactional
    public Reservation create( String flightNumber, String password, int count ) throws FullFlightException {

        // získej let
        Flight flight = em.createNamedQuery( "Flight.findByNumber", Flight.class ).setParameter( "number", flightNumber ).getSingleResult();

        if ( flight == null ) { // chyba vstupu
            throw new IllegalStateException( String.format( "Flight with number '%s' doesn't exists.", flightNumber ) );
        }

        if ( flight.getCapacityLeft() < count ) { // nezbývá dostatek volných míst
            throw new FullFlightException( String.format( "Flight '%s' doesn't have enought capacity left.", flight ) );
        }

        // aktualizuj informace o letu
        flight.setCapacityLeft( flight.getCapacityLeft() - count );

        // vytvoř rezervaci
        Reservation entity = new Reservation();
        entity.setFlight( flight );
        entity.setCount( count );
        entity.setPaid( 0 );
        entity.setCost( flight.getCost() * count );
        entity.setCanceled( false );
        em.persist( entity );
        entity.setPassword( security.hash( entity.getId(), password ) );

        ReservationServiceImpl.log.info( "Reservation with ID '{}' was successfully created.", entity.getId() );

        return entity;
    }

    @Override
    //    @Transactional
    public boolean cancel( long reservation, String password ) throws SecurityException {
        Reservation entity = em.find( Reservation.class, reservation );

        if ( entity == null ) // rezervace neexistuje
        {
            throw new IllegalArgumentException( "Reservation doesn't exists." );
        }

        // zkontroluj přístup k rezervaci
        security.verifyAccess( entity.getId(), entity.getPassword(), password );

        // already canceled
        if ( entity.isCanceled() ) return true;

        // zruš rezervaci
        entity.setCanceled( true );

        // uvolni zabraná místa v letadle
        Flight flight = entity.getFlight();
        flight.setCapacityLeft( flight.getCapacityLeft() + entity.getCount() );

        ReservationServiceImpl.log.info( "Reservation with ID '{}' was successfully canceled.", entity.getId() );

        return true;
    }
}
