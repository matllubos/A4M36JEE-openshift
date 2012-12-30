package cz.cvut.fel.service;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.service.flight.FlightService;
import cz.cvut.fel.util.ArquillianTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.*;

import static cz.cvut.fel.utils.DateUtils.date;
import static cz.cvut.fel.util.ArquillianDataProvider.provide;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( groups = "services" )
public class FlightTest extends ArquillianTest {

    @Inject
    private FlightService service;

    @Test
    public void testFindAll() {

        Collection<Flight> flights = service.findAllFlights();

        assertEquals( flights.size(), 10 );
        assertFlights( flights, "F987545", "F987687", "F987987", "F987126", "F987981", "F987136", "F987358", "F987972", "F987321", "F987963" );
    }

    @Test( dataProvider = "flightsFromProvider" )
    public void findFlightsFrom( Date from, Date to, String destination, String[] expected ) {

        Collection<Flight> flights = service.findFlightsFrom( from, to, destination );

        assertEquals( flights.size(), expected.length );
        assertFlights( flights, expected );
    }

    @DataProvider
    public Object[][] flightsFromProvider() {
        return provide(
                "FlightTest#flightsFromProvider",
                new Object[][]{
                        new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", new String[]{ "F987545", "F987987" } },
                        new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 2, 1, 2012, 23, 59 ), "VIE", new String[]{ "F987687", "F987981" } },
                        new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "INN", new String[]{ "F987972" } }
                } );
    }

    @Test( dataProvider = "flightsToProvider" )
    public void findFlightsTo( Date from, Date to, String destination, String[] expected ) {

        Collection<Flight> flights = service.findFlightsTo( from, to, destination );

        assertEquals( flights.size(), expected.length );
        assertFlights( flights, expected );
    }

    @DataProvider
    public Object[][] flightsToProvider() {
        return provide(
                "FlightTest#flightsToProvider",
                new Object[][]{
                        new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", new String[]{ "F987687" } },
                        new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 2, 1, 2012, 23, 59 ), "MAD", new String[]{ "F987545", "F987126", "F987358" } },
                        new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "LHR", new String[]{ "F987963" } }
                } );
    }

    @Test( dataProvider = "flightsProvider" )
    public void findFlights( Date from, Date to, String destinationFrom, String destinationTo, String[] expected ) {
        Collection<Flight> flights = service.findFlights( from, to, destinationFrom, destinationTo );

        assertEquals( flights.size(), expected.length );
        assertFlights( flights, expected );
    }

    @DataProvider
    public Object[][] flightsProvider() {
        return provide(
                "FlightTest#flightsProvider",
                new Object[][]{
                        new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", "LHR", new String[]{ "F987987" } },
                        new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 3, 1, 2012, 23, 59 ), "PRG", "MAD", new String[]{ "F987545", "F987126", "F987358" } },
                        new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "PRG", "LHR", new String[]{ "F987963" } }
                } );
    }

    private void assertFlights( Collection<Flight> flights, String... model ) {
        // convert values to set
        Set<String> modelSet = new HashSet<String>( Arrays.asList( model ) );

        // extract flight numbers
        Set<String> codes = new HashSet<String>();
        for ( Flight Flight : flights ) {
            codes.add( Flight.getNumber() );
        }

        assertEquals( codes, modelSet );
    }

}
