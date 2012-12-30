package cz.cvut.fel.init;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.Reservation;
import cz.cvut.fel.util.DatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.List;

/**
 * <p>Removes all data from the database.</p>
 *
 * @author Karel Cemus
 */
@Slf4j
@Test( groups = "initialization" )
public class InitializeEmptyDatabaseTest extends DatabaseTest {

    @Test
    public void clearAllReservations() {

        log.trace( "Clearing all reservations in the database." );

        // all flights in the system
        List<Reservation> reservations = em.createNamedQuery( "Reservation.findAll", Reservation.class ).getResultList();

        for ( Reservation reservation : reservations ) {
            log.trace( "Removing '{}'.", reservation );
            em.remove( reservation );
        }
    }

    @Test( dependsOnMethods = "clearAllReservations" )
    public void clearAllFlights() {

        log.trace( "Clearing all flights in the database." );

        // all flights in the system
        List<Flight> flights = em.createNamedQuery( "Flight.findAll", Flight.class ).getResultList();

        for ( Flight flight : flights ) {
            log.trace( "Removing '{}'.", flight );
            em.remove( flight );
        }
    }

    @Test( dependsOnMethods = "clearAllFlights" )
    public void clearAllDestinations() {

        log.trace( "Clearing all destinations in the database." );

        List<Destination> destinations = em.createNamedQuery( "Destination.findAll", Destination.class ).getResultList();

        for ( Destination destination : destinations ) {
            log.trace( "Removing '{}'.", destination );
            em.remove( destination );
        }
    }
}
