package cz.cvut.fel.aos.bookingserver.service.impl;

import cz.cvut.fel.aos.bookingserver.model.Flight;
import cz.cvut.fel.aos.bookingserver.model.Reservation;
import cz.cvut.fel.aos.bookingserver.service.ReservationService;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Karel Cemus
 */
@WebService( endpointInterface = "cz.cvut.fel.aos.bookingserver.service.ReservationService" )
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
    public Reservation create( String flightNumber, String password, int count ) throws IllegalArgumentException {

        // získej let
        Flight flight = em.find( Flight.class, flightNumber );

        if ( flight == null ) // chyba vstupu
            throw new IllegalStateException( String.format( "Flight with number '%s' doesn't exists.", flightNumber ) );

        if ( flight.getCapacityLeft() < count ) // nezbývá dostatek volných míst
            throw new IllegalArgumentException( String.format( "Flight '%s' doesn't have enought capacity left.", flight ) );

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

        return entity;
    }

    @Override
    public boolean cancel( long reservation, String password ) throws SecurityException {
        Reservation entity = em.find( Reservation.class, reservation );

        if ( entity == null ) // rezervace neexistuje
            throw new IllegalArgumentException( "Reservation doesn't exists." );

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        // zruš rezervaci
        entity.setCanceled( true );

        return true;
    }

    @Override
    public Reservation pay( long reservation, String password, int amount ) {
        Reservation entity = em.find( Reservation.class, reservation );

        if ( entity == null ) // rezervace neexistuje
            throw new IllegalArgumentException( "Reservation doesn't exists." );

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        // vlož platbu
        entity.setPaid( entity.getPaid() + amount );

        return entity;
    }

    @Override
    public int withdrawCredit( long reservation, String password, int amount ) {
        Reservation entity = em.find( Reservation.class, reservation );

        if ( entity == null ) // rezervace neexistuje
            throw new IllegalArgumentException( "Reservation doesn't exists." );

        // zkontroluj přístup k rezervaci
        checkSecurity( entity, password );

        // omez množství vybraných peněz a vyber je
        amount = Math.min( amount, entity.getPaid() );
        entity.setPaid( entity.getPaid() - amount );

        return amount;
    }

    private void checkSecurity( Reservation reservation, String password ) throws SecurityException {
        if ( reservation != null && !reservation.getPassword().equalsIgnoreCase( password ) )
            throw new SecurityException( String.format( "Access to reservation with id '%d' is forbidden. Password is incorrect.", reservation.getId() ) );
    }
}
