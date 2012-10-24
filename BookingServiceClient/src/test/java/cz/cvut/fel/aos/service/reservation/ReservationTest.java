package cz.cvut.fel.aos.service.reservation;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = true )
public class ReservationTest {

    ReservationService service;

    @BeforeTest
    public void setUp() {
        ReservationServiceImplService factory = new ReservationServiceImplService( ReservationServiceImplService.WSDL_LOCATION, ReservationServiceImplService.SERVICE );
        service = factory.getReservationServiceImplPort();
    }


    public void testCreate() throws FullFlightException {

        Reservation reservation = service.create( "F987545", "heslo12345", 5 );

        assertEquals( reservation.getCount(), 5 );
        assertEquals( reservation.getPaid(), 0 );
        assertEquals( reservation.getFlight().getNumber(), "F987545" );
        assertEquals( reservation.getCost(), 5000 * 5 );
        assertEquals( reservation.getPassword(), "heslo12345" );
        assertNotNull( reservation.getId());
    }

    //        {
    //            System.out.println("Invoking cancel...");
    //            long _cancel_reservation = 0;
    //            java.lang.String _cancel_password = "";
    //            try {
    //                boolean _cancel__return = port.cancel(_cancel_reservation, _cancel_password);
    //                System.out.println("cancel.result=" + _cancel__return);
    //
    //            } catch (SecurityException e) {
    //                System.out.println("Expected exception: SecurityException has occurred.");
    //                System.out.println(e.toString());
    //            }
    //        }
    //        {
    //            System.out.println("Invoking pay...");
    //            long _pay_reservation = 0;
    //            java.lang.String _pay_password = "";
    //            int _pay_amount = 0;
    //            try {
    //                cz.cvut.fel.aos.service.reservation.Reservation _pay__return = port.pay(_pay_reservation, _pay_password, _pay_amount);
    //                System.out.println("pay.result=" + _pay__return);
    //
    //            } catch (SecurityException e) {
    //                System.out.println("Expected exception: SecurityException has occurred.");
    //                System.out.println(e.toString());
    //            }
    //        }
    //        {
    //            System.out.println("Invoking withdrawCredit...");
    //            long _withdrawCredit_reservation = 0;
    //            java.lang.String _withdrawCredit_password = "";
    //            int _withdrawCredit_amount = 0;
    //            try {
    //                int _withdrawCredit__return = port.withdrawCredit(_withdrawCredit_reservation, _withdrawCredit_password, _withdrawCredit_amount);
    //                System.out.println("withdrawCredit.result=" + _withdrawCredit__return);
    //
    //            } catch (SecurityException e) {
    //                System.out.println("Expected exception: SecurityException has occurred.");
    //                System.out.println(e.toString());
    //            }
    //        }
    //        {
    //            System.out.println("Invoking create...");
    //            java.lang.String _create_flightNumber = "";
    //            java.lang.String _create_password = "";
    //            int _create_count = 0;
    //            try {
    //                cz.cvut.fel.aos.service.reservation.Reservation _create__return = port.create(_create_flightNumber, _create_password, _create_count);
    //                System.out.println("create.result=" + _create__return);
    //
    //            } catch (FullFlightException e) {
    //                System.out.println("Expected exception: FullFlightException has occurred.");
    //                System.out.println(e.toString());
    //            }
    //        }
    //        {
    //            System.out.println("Invoking find...");
    //            long _find_reservation = 0;
    //            java.lang.String _find_password = "";
    //            try {
    //                cz.cvut.fel.aos.service.reservation.Reservation _find__return = port.find(_find_reservation, _find_password);
    //                System.out.println("find.result=" + _find__return);
    //
    //            } catch (SecurityException e) {
    //                System.out.println("Expected exception: SecurityException has occurred.");
    //                System.out.println(e.toString());
    //            }
    //        }

    //    @Test
    //    public void testFindAll() {
    //
    //        Collection<Flight> flights = service.findAll();
    //
    //        assertEquals( flights.size(), 10 );
    //        assertFlights( flights, "F987545", "F987687", "F987987", "F987126", "F987981", "F987136", "F987358", "F987972", "F987321", "F987963" );
    //    }
    //
    //    @Test( dataProvider = "flightsFromProvider" )
    //    public void findFlightsFrom( Date from, Date to, String destination, String[] expected ) {
    //
    //        Collection<Flight> flights = service.findFlightsFrom( from, to, destination );
    //
    //        assertEquals( flights.size(), expected.length );
    //        assertFlights( flights, expected );
    //    }
    //
    //    @DataProvider
    //    public Object[][] flightsFromProvider() {
    //        return new Object[][]{
    //                new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", new String[]{ "F987545", "F987987" } },
    //                new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 2, 1, 2012, 23, 59 ), "VIE", new String[]{ "F987687", "F987981" } },
    //                new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "INN", new String[]{ "F987972" } }
    //        };
    //    }
    //
    //    @Test( dataProvider = "flightsToProvider" )
    //    public void findFlightsTo( Date from, Date to, String destination, String[] expected ) {
    //
    //        Collection<Flight> flights = service.findFlightsTo( from, to, destination );
    //
    //        assertEquals( flights.size(), expected.length );
    //        assertFlights( flights, expected );
    //    }
    //
    //    @DataProvider
    //    public Object[][] flightsToProvider() {
    //        return new Object[][]{
    //                new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", new String[]{ "F987687" } },
    //                new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 2, 1, 2012, 23, 59 ), "MAD", new String[]{ "F987545", "F987126", "F987358" } },
    //                new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "LHR", new String[]{ "F987963" } }
    //        };
    //    }
    //
    //    @Test( dataProvider = "flightsProvider" )
    //    public void findFlights( Date from, Date to, String destinationFrom, String destinationTo, String[] expected ) {
    //        Collection<Flight> flights = service.findFlights( from, to, destinationFrom, destinationTo );
    //
    //        assertEquals( flights.size(), expected.length );
    //        assertFlights( flights, expected );
    //    }
    //
    //    @DataProvider
    //    public Object[][] flightsProvider() {
    //        return new Object[][]{
    //                new Object[]{ date( 1, 1, 2012, 0, 0 ), date( 1, 1, 2012, 23, 59 ), "PRG", "LHR", new String[]{ "F987987" } },
    //                new Object[]{ date( 1, 1, 2012, 8, 30 ), date( 3, 1, 2012, 23, 59 ), "PRG", "MAD", new String[]{ "F987545", "F987126", "F987358" } },
    //                new Object[]{ date( 3, 1, 2012, 0, 0 ), date( 3, 1, 2012, 23, 59 ), "PRG", "LHR", new String[]{ "F987963" } }
    //        };
    //    }
    //
    //    private void assertFlights( Collection<Flight> flights, String... model ) {
    //        // convert values to set
    //        Set<String> modelSet = new HashSet<String>( Arrays.asList( model ) );
    //
    //        // extract flight numbers
    //        Set<String> codes = new HashSet<String>();
    //        for ( Flight Flight : flights ) {
    //            codes.add( Flight.getNumber() );
    //        }
    //
    //        assertEquals( codes, modelSet );
    //    }

}
