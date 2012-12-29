package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.model.Destination;
import cz.cvut.fel.aos.model.Flight;
import cz.cvut.fel.aos.model.Reservation;
import cz.cvut.fel.aos.service.facade.FacadeService;
import cz.cvut.fel.aos.service.facade.SuccessfulReservation;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.activation.DataHandler;
import java.text.SimpleDateFormat;
import java.util.*;

import static cz.cvut.fel.aos.utils.DateUtils.date;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = false )
public class FacadeServiceTest {

    private static final String PASSWORD = "heslo12345";

    private long reservationId;

    FacadeService service;

    @BeforeTest
    public void setUp() {
        //        FacadeServiceImplService factory = new FacadeServiceImplService();
        //        service = factory.getFacadeServiceImplPort();
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
    public void testCreateReservation() throws Exception {
        SuccessfulReservation container = service.createReservation( "F987545", PASSWORD, 5 );

        Reservation reservation = container.getReservation();
        Assert.assertEquals( reservation.getCount(), 5 );
        Assert.assertEquals( reservation.getPaid(), 0 );
        Assert.assertEquals( reservation.getFlight().getNumber(), "F987545" );
        Assert.assertEquals( reservation.getCost(), 5000 * 5 );
        assertNotNull( reservationId = reservation.getId() );

        byte[] buffer = new byte[ 1000000 ];
        int read = container.getConfirmation().getInputStream().read( buffer );

        String confirmation = new String( buffer, 0, read );

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
        Assert.assertEquals( reservation.getCount(), 5 );
        Assert.assertEquals( reservation.getFlight().getNumber(), "F987545" );
        Assert.assertEquals( reservation.getCost(), 5000 * 5 );
    }

    @Test( dependsOnMethods = "testCreateReservation", expectedExceptions = SecurityException.class )
    public void testFindReservationWithException() throws SecurityException {
        service.findReservation( reservationId, "wrongPassword" );
        fail( "Expected exception" );
    }


    @Test( dependsOnMethods = "testFindReservation" )
    public void testPayVisa() throws Exception {
        // assumption
        Reservation before = service.findReservation( reservationId, PASSWORD );
        Assert.assertEquals( before.getPaid(), 0 );

        DataHandler response = service.payVisa( reservationId, PASSWORD, "Jan Novak", 98745600, date( 5, 6, 2014 ), 600 );

        Reservation after = service.findReservation( reservationId, PASSWORD );
        Assert.assertEquals( after.getPaid(), after.getCost() );

        byte[] buffer = new byte[ 1024 * 1024 ];
        int read = response.getInputStream().read( buffer );
        String confirmation = new String( buffer, 0, read );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Potvrzení o přijetí platby\n" +
                "---------------------------------------------------------------------------\n" +
                "    Číslo rezervace:\t" + reservationId + "\n" +
                "     Kreditní karta:\t98745600\n" +
                "     Přijatá částka:\t" + before.getCost() + "\n" +
                "       Datum platby:\t" + new SimpleDateFormat( "dd.MM.yyyy HH:mm" ).format( new Date() ) + "\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( confirmation, expected );
    }


    @Test( dependsOnMethods = "testPayVisa" )
    public void testPrintTicket() throws Exception {
        Reservation reservation = service.findReservation( reservationId, PASSWORD );
        Flight flight = reservation.getFlight();
        DataHandler handler = service.printTicket( reservationId, PASSWORD );
        byte[] buffer = new byte[ 1024 * 1024 ];
        int read = handler.getInputStream().read( buffer );
        String confirmation = new String( buffer, 0, read );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "E-letenka\n" +
                "---------------------------------------------------------------------------\n" +
                "    Číslo rezervace:\t" + reservationId + "\n" +
                "         Číslo letu:\t" + flight.getNumber() + "\n" +
                "            Odlet z:\t" + flight.getFrom().getName() + "\n" +
                "          Přílet do:\t" + flight.getTo().getName() + "\n" +
                "       Datum odletu:\t01.01.2012\n" +
                "         Čas odletu:\t10:20\n" +
                "               Stav:\tzaplaceno\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( confirmation, expected );
    }


    @Test( dependsOnMethods = "testPrintTicket" )
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

        Assert.assertEquals( updatedFlight.getCapacityLeft(), freeSpace + reservation.getCount() );
    }

    @Test( dependsOnMethods = "testCancelReservation" )
    public void testPayFromCanceledReservation() throws Exception {

        // init another reservation
        SuccessfulReservation container = service.createReservation( "F987545", PASSWORD, 4 );
        Reservation reservation = container.getReservation();

        try {
            Assert.assertEquals( reservation.getCount(), 4 );
            Assert.assertEquals( reservation.getPaid(), 0 );
            Assert.assertEquals( reservation.getFlight().getNumber(), "F987545" );
            Assert.assertEquals( reservation.getCost(), 5000 * 4 );

            // verify assumptions
            Reservation canceled = service.findReservation( reservationId, PASSWORD );
            assertTrue( canceled.isCanceled() );
            Assert.assertEquals( canceled.getPaid(), 5 * 5000 );


            // execute test
            assertTrue( service.payFromCanceledReservation( reservationId, PASSWORD, reservation.getId(), PASSWORD ) );

            // verify
            canceled = service.findReservation( reservationId, PASSWORD );
            reservation = service.findReservation( reservation.getId(), PASSWORD );

            Assert.assertEquals( canceled.getPaid(), 1 * 5000 );
            Assert.assertEquals( reservation.getPaid(), 4 * 5000 );
            Assert.assertEquals( reservation.getPaid(), reservation.getCost() );
        } finally {
            service.cancelReservation( reservation.getId(), PASSWORD );
        }
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
