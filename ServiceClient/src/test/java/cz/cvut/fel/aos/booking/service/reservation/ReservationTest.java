package cz.cvut.fel.aos.booking.service.reservation;

import cz.cvut.fel.aos.booking.service.flight.Flight;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = true )
public class ReservationTest {

    private static final String PASSWORD = "heslo12345";

    ReservationService service;

    private long reservationId;

    @BeforeTest
    public void setUp() {
        ReservationServiceImplService factory = new ReservationServiceImplService( ReservationServiceImplService.WSDL_LOCATION, ReservationServiceImplService.SERVICE );
        service = factory.getReservationServiceImplPort();
    }


    public void testCreate() throws FullFlightException {

        Reservation reservation = service.create( "F987545", PASSWORD, 5 );

        assertEquals( reservation.getCount(), 5 );
        assertEquals( reservation.getPaid(), 0 );
        assertEquals( reservation.getFlight().getNumber(), "F987545" );
        assertEquals( reservation.getCost(), 5000 * 5 );
        assertEquals( reservation.getPassword(), "heslo12345" );
        assertNotNull( reservation.getId() );

        // set up of another tests
        reservationId = reservation.getId();
    }

    @Test( dependsOnMethods = "testCreate" )
    public void testFind() throws SecurityException {
        Reservation reservation = service.find( reservationId, PASSWORD );

        assertNotNull( reservation.getId() );
        assertEquals( reservation.getCount(), 5 );
        assertEquals( reservation.getFlight().getNumber(), "F987545" );
        assertEquals( reservation.getCost(), 5000 * 5 );
    }

    @Test( dependsOnMethods = "testCreate", expectedExceptions = SecurityException.class )
    public void testFindWithException() throws SecurityException {
        service.find( reservationId, "wrongPassword" );
        fail( "Expected exception" );
    }

    @Test( dependsOnMethods = { "testFind", "testFindWithException" } )
    public void testCancel() throws SecurityException {
        Reservation reservation = service.find( reservationId, PASSWORD );
        Flight flight = reservation.getFlight();
        int freeSpace = flight.getCapacityLeft();

        // verify assumption
        assertFalse( reservation.isCanceled() );

        // execute && verify
        assertTrue( service.cancel( reservationId, PASSWORD ) );

        // verify freed capacity in the flight
        Reservation canceled = service.find( reservationId, PASSWORD );
        Flight updatedFlight = canceled.getFlight();

        assertEquals( updatedFlight.getCapacityLeft(), freeSpace + reservation.getCount() );
    }

//    @Test( dependsOnMethods = "testCreate" )
//    public void testPay() throws SecurityException {
//        Reservation reservation = service.find( reservationId, PASSWORD );
//        int paid = reservation.getPaid();
//
//        // execute
//        Reservation paidReservation = service.pay( reservationId, PASSWORD, 1000 );
//
//        assertEquals( paidReservation.getPaid(), paid + 1000 );
//    }

//    @Test( dependsOnMethods = "testPay" )
//    public void testWithdraw() throws SecurityException {
//        Reservation reservation = service.find( reservationId, PASSWORD );
//
//        int paid = reservation.getPaid();
//
//        // execute
//        int withdrawn = service.withdrawCredit( reservationId, PASSWORD, 1000 );
//
//        Reservation updated = service.find( reservationId, PASSWORD );
//        assertEquals( withdrawn, 1000 );
//        assertEquals( updated.getPaid(), paid - 1000 );
//    }
}
