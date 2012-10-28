package cz.cvut.fel.aos.booking.service.reservation;

import cz.cvut.fel.aos.model.Flight;
import cz.cvut.fel.aos.model.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** @author Karel Cemus */
@Slf4j
@WebService( endpointInterface = "cz.cvut.fel.aos.booking.service.reservation.ReservationService" )
public class ReservationServiceImpl implements ReservationService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Reservation find( long reservation, String password ) throws SecurityException {
        Reservation entity = em.find( Reservation.class, reservation );

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        return entity;
    }

    @Override
    @Transactional
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
        entity.setPassword( password );
        em.persist( entity );

        ReservationServiceImpl.log.info( "Reservation with ID '{}' was successfully created.", entity.getId() );

        return entity;
    }

    @Override
    @Transactional
    public boolean cancel( long reservation, String password ) throws SecurityException {
        Reservation entity = em.find( Reservation.class, reservation );

        if ( entity == null ) // rezervace neexistuje
        {
            throw new IllegalArgumentException( "Reservation doesn't exists." );
        }

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        // zruš rezervaci
        entity.setCanceled( true );

        // uvolni zabraná místa v letadle
        Flight flight = entity.getFlight();
        flight.setCapacityLeft( flight.getCapacityLeft() + entity.getCount() );

        ReservationServiceImpl.log.info( "Reservation with ID '{}' was successfully canceled.", entity.getId() );

        return true;
    }

    @Override
    @Transactional
    public Reservation pay( long reservation, String password, int amount ) {
        Reservation entity = em.find( Reservation.class, reservation );

        if ( entity == null ) // rezervace neexistuje
        {
            throw new IllegalArgumentException( "Reservation doesn't exists." );
        }

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        // vlož platbu
        entity.setPaid( entity.getPaid() + amount );

        ReservationServiceImpl.log.info( "Reservation with ID '{}' received '{}' money. There is '{}'.", new Object[]{ entity.getId(), amount, entity.getPaid() } );

        return entity;
    }

    @Override
    @Transactional
    public int withdrawCredit( long reservation, String password, int amount ) {
        Reservation entity = em.find( Reservation.class, reservation );

        if ( entity == null ) // rezervace neexistuje
        {
            throw new IllegalArgumentException( "Reservation doesn't exists." );
        }

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        // omez množství vybraných peněz a vyber je
        amount = Math.min( amount, entity.getPaid() );
        entity.setPaid( entity.getPaid() - amount );

        ReservationServiceImpl.log.info( "Reservation with ID '{}' withdrawn '{}' money. There left '{}'.", new Object[]{ entity.getId(), amount, entity.getPaid() } );

        return amount;
    }

    private void checkSecurity( Reservation reservation, String password ) throws SecurityException {
        if ( reservation != null && !reservation.getPassword().equalsIgnoreCase( password ) ) {
            throw new SecurityException( String.format( "Access to reservation with id '%d' is forbidden. Password is incorrect.", reservation.getId() ) );
        }
    }
}
