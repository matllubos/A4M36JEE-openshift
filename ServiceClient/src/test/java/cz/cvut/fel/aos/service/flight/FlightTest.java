package cz.cvut.fel.aos.service.flight;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = true )
public class FlightTest {

    FlightService service;

    @BeforeTest
    public void setUp() {
        FlightServiceImplService factory = new FlightServiceImplService( FlightServiceImplService.WSDL_LOCATION, FlightServiceImplService.SERVICE );
        service = factory.getFlightServiceImplPort();
    }

    @Test
    public void testFindAll() {

        Collection<Flight> flights = service.findAll();
        assertEquals( flights.size(), 10 );

        assertFlights( flights, "F987545", "F987687", "F987987", "F987126", "F987981", "F987136", "F987358", "F987972", "F987321", "F987963" );
    }

    @Test
    public void findFlightsFrom( Date from, Date to, String destination, String... expected ) {
        Collection<Flight> flights =null;// service.findFlightsFrom( from, to, destination );
        assertEquals( flights.size(), 10 );

        assertFlights( flights, expected );
    }

    @Test
    public void findFlightsTo() {
    }

    @Test
    public void findFlights() {
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
