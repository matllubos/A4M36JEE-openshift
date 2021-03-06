package cz.cvut.fel.init;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.FlightStatus;
import cz.cvut.fel.model.Time;
import cz.cvut.fel.util.DatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

import static cz.cvut.fel.util.ArquillianDataProvider.provide;
import static cz.cvut.fel.utils.DateUtils.date;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Slf4j
@Test( groups = "initialization", dependsOnMethods = "cz.cvut.fel.init.InitializeDestinationTest.insert" )
public class InitializeFlightTest extends DatabaseTest {

    @Test( dataProvider = "flightProvider" )
    public void insert( String number, Date departure, Date arrival, String from, String to, int capacity, int cost, FlightStatus status, boolean deleted ) {

        Flight flight = new Flight();
        flight.setNumber( number );
        flight.setDeparture( new Time( departure ) );
        flight.setArrival( new Time( arrival ) );
        flight.setFrom( getDestination( from ) );
        flight.setTo( getDestination( to ) );
        flight.setCapacity( capacity );
        flight.setSeatsTaken( 0 );
        flight.setCost( cost );
        flight.setStatus( status );
        if ( deleted ) flight.invalidate();

        log.trace( "Saving '{}'", flight );
        em.persist( flight );

        assertFalse( flight.getId() == 0 );
    }


    @DataProvider
    public Object[][] flightProvider() {
        return provide( "InitializeFlightTest#flightProvider", new Object[][]{
                new Object[]{ "F987545", date( 1, 1, 2012, 10, 20 ), date( 1, 1, 2012, 14, 20 ), "PRG", "MAD", 100, 5000, FlightStatus.SCHEDULED, false },
                new Object[]{ "F987687", date( 1, 1, 2012, 8, 30 ), date( 1, 1, 2012, 10, 10 ), "VIE", "PRG", 80, 3000, FlightStatus.CANCELLED, false },
                new Object[]{ "F987987", date( 1, 1, 2012, 14, 40 ), date( 1, 1, 2012, 17, 15 ), "PRG", "LHR", 130, 7000, FlightStatus.DELAYED, false },
                new Object[]{ "F987126", date( 2, 1, 2012, 9, 10 ), date( 2, 1, 2012, 13, 20 ), "PRG", "MAD", 110, 5000, FlightStatus.SCHEDULED, false },
                new Object[]{ "F987981", date( 2, 1, 2012, 10, 30 ), date( 2, 1, 2012, 12, 30 ), "VIE", "PRG", 90, 3000, FlightStatus.SCHEDULED, false },
                new Object[]{ "F987136", date( 2, 1, 2012, 12, 50 ), date( 2, 1, 2012, 15, 40 ), "LHR", "PRG", 100, 7000, FlightStatus.SCHEDULED, false },
                new Object[]{ "F987358", date( 2, 1, 2012, 23, 0 ), date( 3, 1, 2012, 4, 10 ), "PRG", "MAD", 110, 5000, FlightStatus.SCHEDULED, false },
                new Object[]{ "F987972", date( 3, 1, 2012, 7, 10 ), date( 3, 1, 2012, 9, 20 ), "INN", "PRG", 120, 4000, FlightStatus.SCHEDULED, false },
                new Object[]{ "F987321", date( 3, 1, 2012, 16, 20 ), date( 3, 1, 2012, 18, 20 ), "PRG", "VIE", 130, 3000, FlightStatus.SCHEDULED, false },
                new Object[]{ "F987963", date( 3, 1, 2012, 17, 40 ), date( 3, 1, 2012, 20, 50 ), "PRG", "LHR", 80, 7000, FlightStatus.SCHEDULED, false },
                new Object[]{ "F987235", date( 3, 1, 2011, 17, 40 ), date( 3, 1, 2012, 20, 50 ), "PRG", "LHR", 80, 7000, FlightStatus.LANDED, false },
                new Object[]{ "F987321", date( 3, 1, 2012, 16, 20 ), date( 3, 1, 2012, 18, 20 ), "PRG", "VIE", 130, 3000, FlightStatus.SCHEDULED, true },
                new Object[]{ "F987111", date( 3, 1, 2012, 17, 40 ), date( 3, 1, 2012, 20, 50 ), "PRG", "LHR", 80, 7000, FlightStatus.SCHEDULED, true }
        } );
    }

    private Destination getDestination( String code ) {
        return em.createNamedQuery( "Destination.findByCode", Destination.class ).setParameter( "code", code ).getSingleResult();
    }
}
