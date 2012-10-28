package cz.cvut.fel.aos.booking.insert;

import cz.cvut.fel.aos.booking.model.Destination;
import cz.cvut.fel.aos.booking.model.Flight;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static cz.cvut.fel.aos.booking.utils.DateUtils.date;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = false )
public class InsertFlight extends DatabaseTest {

    @Test
    public void clearAll() {

        // all flights in the system
        List<Flight> flights = em.createNamedQuery( "Flight.findAll", Flight.class ).getResultList();

        for ( Flight flight : flights )
            em.remove( flight );
    }

    @Test( dataProvider = "flightProvider", dependsOnMethods = "clearAll" )
    public void run( String number, Date departure, Date arrival, Destination from, Destination to, int capacity, int cost ) {

        Flight flight = new Flight();
        flight.setNumber( number );
        flight.setDeparture( departure );
        flight.setArrival( arrival );
        flight.setFrom( from );
        flight.setTo( to );
        flight.setCapacity( capacity );
        flight.setCapacityLeft( capacity );
        flight.setCost( cost );

        em.persist( flight );

        assertFalse( flight.getId() == 0 );
    }


    @DataProvider
    public Object[][] flightProvider() {
        return new Object[][]{
                new Object[]{ "F987545", date( 1, 1, 2012, 10, 20 ), date( 1, 1, 2012, 14, 20 ), getDestination( "PRG" ), getDestination( "MAD" ), 100, 5000 },
                new Object[]{ "F987687", date( 1, 1, 2012, 8, 30 ), date( 1, 1, 2012, 10, 10 ), getDestination( "VIE" ), getDestination( "PRG" ), 80, 3000 },
                new Object[]{ "F987987", date( 1, 1, 2012, 14, 40 ), date( 1, 1, 2012, 17, 15 ), getDestination( "PRG" ), getDestination( "LHR" ), 130, 7000 },
                new Object[]{ "F987126", date( 2, 1, 2012, 9, 10 ), date( 2, 1, 2012, 13, 20 ), getDestination( "PRG" ), getDestination( "MAD" ), 110, 5000 },
                new Object[]{ "F987981", date( 2, 1, 2012, 10, 30 ), date( 2, 1, 2012, 12, 30 ), getDestination( "VIE" ), getDestination( "PRG" ), 90, 3000 },
                new Object[]{ "F987136", date( 2, 1, 2012, 12, 50 ), date( 2, 1, 2012, 15, 40 ), getDestination( "LHR" ), getDestination( "PRG" ), 100, 7000 },
                new Object[]{ "F987358", date( 2, 1, 2012, 23, 0 ), date( 3, 1, 2012, 4, 10 ), getDestination( "PRG" ), getDestination( "MAD" ), 110, 5000 },
                new Object[]{ "F987972", date( 3, 1, 2012, 7, 10 ), date( 3, 1, 2012, 9, 20 ), getDestination( "INN" ), getDestination( "PRG" ), 120, 4000 },
                new Object[]{ "F987321", date( 3, 1, 2012, 16, 20 ), date( 3, 1, 2012, 18, 20 ), getDestination( "PRG" ), getDestination( "VIE" ), 130, 3000 },
                new Object[]{ "F987963", date( 3, 1, 2012, 17, 40 ), date( 3, 1, 2012, 20, 50 ), getDestination( "PRG" ), getDestination( "LHR" ), 80, 7000 }
        };
    }

    private Destination getDestination( String code ) {
        return em.createNamedQuery( "Destination.findByCode", Destination.class ).
                setParameter( "code", code ).
                getSingleResult();
    }
}
