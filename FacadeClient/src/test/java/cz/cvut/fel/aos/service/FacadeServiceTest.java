package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.service.facade.*;
import cz.cvut.fel.aos.service.facade.SecurityException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.activation.DataHandler;
import java.util.*;

import static cz.cvut.fel.aos.utils.DateUtils.date;
import static org.testng.Assert.*;

/** @author Karel Cemus */
public class FacadeServiceTest {

    private static final String PASSWORD = "heslo12345";

    private long reservationId;

    FacadeService service;

    @BeforeTest
    public void setUp() {
        FacadeServiceImplService factory = new FacadeServiceImplService();
        service = factory.getFacadeServiceImplPort();
    }

    @Test
    public void testListAllDestinations() throws Exception {
        Collection<Destination> destinations = service.findAllDestinations();
        assertEquals( destinations.size(), 5 );

        Set<String> codes = new HashSet<String>();
        for ( Destination destination : destinations ) {
            codes.add( destination.getCode() );
        }

        assertEquals( codes, new HashSet<String>( Arrays.asList( new String[]{ "PRG", "MAD", "LHR", "INN", "VIE" } ) ) );
    }

    @Test( dataProvider = "flightsFromProvider" )
    public void findFlightsFrom( Date from, Date to, String destination, String[] expected ) {

        Collection<Flight> flights = service.findFlightsFrom( from, to, destination );

        assertEquals( flights.size(), expected.length );
        assertFlights( flights, expected );
    }

    @DataProvider
    public Object[][] flightsFromProvider() {
        return new Object[][]{
                new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", new String[]{ "F987545", "F987987" } },
                new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 2, 1, 2012, 23, 59 ), "VIE", new String[]{ "F987687", "F987981" } },
                new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "INN", new String[]{ "F987972" } }
        };
    }

    @Test( dataProvider = "flightsToProvider" )
    public void findFlightsTo( Date from, Date to, String destination, String[] expected ) {

        Collection<Flight> flights = service.findFlightsTo( from, to, destination );

        assertEquals( flights.size(), expected.length );
        assertFlights( flights, expected );
    }

    @DataProvider
    public Object[][] flightsToProvider() {
        return new Object[][]{
                new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", new String[]{ "F987687" } },
                new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 2, 1, 2012, 23, 59 ), "MAD", new String[]{ "F987545", "F987126", "F987358" } },
                new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "LHR", new String[]{ "F987963" } }
        };
    }

    @Test( dataProvider = "flightsProvider" )
    public void findFlights( Date from, Date to, String destinationFrom, String destinationTo, String[] expected ) {
        Collection<Flight> flights = service.findFlights( from, to, destinationFrom, destinationTo );

        assertEquals( flights.size(), expected.length );
        assertFlights( flights, expected );
    }

    @DataProvider
    public Object[][] flightsProvider() {
        return new Object[][]{
                new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", "LHR", new String[]{ "F987987" } },
                new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 3, 1, 2012, 23, 59 ), "PRG", "MAD", new String[]{ "F987545", "F987126", "F987358" } },
                new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "PRG", "LHR", new String[]{ "F987963" } }
        };
    }

    @Test
    public void testPayFromCanceledReservation() throws Exception {

    }

    @Test
    public void testPrintTicket() throws Exception {

    }

    @Test
    public void testCreateReservation() throws Exception {
        SuccessfulReservation container = service.createReservation( "F987545", PASSWORD, 5 );

        Reservation reservation = container.getReservation();
        assertEquals( reservation.getCount(), 5 );
        assertEquals( reservation.getPaid(), 0 );
        assertEquals( reservation.getFlight().getNumber(), "F987545" );
        assertEquals( reservation.getCost(), 5000 * 5 );
        assertEquals( reservation.getPassword(), PASSWORD );
        assertNotNull( reservationId = reservation.getId() );

        String confirmation = new String( container.getConfirmation() );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Potvrzení o rezervaci\n" +
                "---------------------------------------------------------------------------\n" +
                "    Číslo rezervace:\t" + reservation.getId() + "\n" +
                "  Heslo pro přístup:\t" + PASSWORD + "\n" +
                "         Číslo letu:\t" + reservation.getFlight().getNumber() + "\n" +
                "            Odlet z:\t" + reservation.getFlight().getFrom().getName() + "\n" +
                "          Přílet do:\t" + reservation.getFlight().getTo().getName() + "\n" +
                "       Datum odletu:\t01.01.2012\n" +
                "         Čas odletu:\t10:20\n" +
                "               Cena:\t" + reservation.getCost() + " Kč\n" +
                "---------------------------------------------------------------------------\n";

        System.out.println( confirmation );
        assertEquals( confirmation, expected );
    }

    @Test( dependsOnMethods = "testCreateReservation" )
    public void testFindReservation() throws Exception {
        Reservation reservation = service.findReservation( reservationId, PASSWORD );

        assertNotNull( reservation.getId() );
        assertEquals( reservation.getCount(), 5 );
        assertEquals( reservation.getFlight().getNumber(), "F987545" );
        assertEquals( reservation.getCost(), 5000 * 5 );
    }

    @Test( dependsOnMethods = "testCreateReservation", expectedExceptions = SecurityException.class )
    public void testFindReservationWithException() throws SecurityException {
        service.findReservation( reservationId, "wrongPassword" );
        fail( "Expected exception" );
    }

    @Test( dependsOnMethods = { "testFindReservation", "testFindReservationWithException" } )
    public void testCancelReservation() throws Exception {
        Reservation reservation = service.findReservation( reservationId, PASSWORD );
        Flight flight = reservation.getFlight();
        int freeSpace = flight.getCapacityLeft();

        // verify assumption
        assertFalse( reservation.isCanceled() );

        // execute && verify
        DataHandler handler = service.cancelReservation( reservationId, PASSWORD );
        byte[] buffer = new byte[ 1024 * 1024 ];
        int read = handler.getInputStream().read( buffer );
        String confirmation = new String( buffer, 0, read );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Rezervace zrušena\n" +
                "---------------------------------------------------------------------------\n" +
                "    Číslo rezervace:\t" + reservationId + "\n" +
                "         Číslo letu:\t" + reservation.getFlight().getNumber() + "\n" +
                "            Odlet z:\t" + reservation.getFlight().getFrom().getName() + "\n" +
                "          Přílet do:\t" + reservation.getFlight().getTo().getName() + "\n" +
                "       Datum odletu:\t01.01.2012\n" +
                "         Čas odletu:\t10:20\n" +
                "---------------------------------------------------------------------------\n";

        System.out.println( confirmation );
        assertEquals( confirmation, expected );

        // verify freed capacity in the flight
        Reservation canceled = service.findReservation( reservationId, PASSWORD );
        Flight updatedFlight = canceled.getFlight();

        assertEquals( updatedFlight.getCapacityLeft(), freeSpace + reservation.getCount() );
    }


    @Test
    public void testPayVisa() throws Exception {

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
