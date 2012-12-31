package cz.cvut.fel.service;

import cz.cvut.fel.exception.*;
import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.Reservation;
import cz.cvut.fel.util.ArquillianTest;
import cz.cvut.fel.utils.SecurityProvider;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import javax.ejb.EJBException;
import javax.inject.Inject;

import static org.testng.Assert.*;

/** @author Karel Cemus */
@Slf4j
@Test( groups = "services" )
public class ReservationTest extends ArquillianTest {

    private static final String PASSWORD = "heslo12345";

    @Inject
    private ReservationService service;

    @Inject
    private SecurityProvider security;

    private static long reservationId;

    public void testCreate() throws FullFlightException {

        Reservation reservation = service.create( "F987545", PASSWORD, 5 );

        assertEquals( reservation.getCount(), 5 );
        assertEquals( reservation.getPaid(), 0 );
        assertEquals( reservation.getFlight().getNumber(), "F987545" );
        assertEquals( reservation.getCost(), 5000 * 5 );
        assertEquals( reservation.getPassword(), security.hash( reservation.getId(), PASSWORD ) );
        assertTrue( reservation.getId() != 0 );
        log.trace( "Created reservation with id '{}'.", reservation.getId() );

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

    @Test( dependsOnMethods = "testCreate" )
    public void testFind_WrongPassword() throws SecurityException {
        try {
            service.find( reservationId, "wrongPassword" );
            fail( "Expected exception" );
        } catch ( SecurityException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof SecurityException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test
    public void testFind_NotExistent() throws SecurityException {
        try {
            service.find( 9999999999L, "wrongPassword" );
            fail( "Expected exception" );
        } catch ( NoSuchReservationException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof NoSuchReservationException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test( dependsOnMethods = {
            "testFind", "testFind_NotExistent", "testFind_WrongPassword",
            "testPrintCancelConfirmation_NotCancelled",
            "testPrintConfirmation", "testPrintConfirmation_WrongPassword",
            "testPrintTicket", "testPrintTicket_WrongPassword", "testPrintTicket_NotPaid"
    }, alwaysRun = true )
    public void testCancel() throws SecurityException {
        Reservation reservation = service.find( reservationId, PASSWORD );
        Flight flight = reservation.getFlight();
        int freeSpace = flight.getCapacityLeft();

        // verify assumption
        assertFalse( reservation.isCancelled() );

        // perform test
        service.cancel( reservationId, PASSWORD );

        // verify
        reservation = service.find( reservationId, PASSWORD );
        assertTrue( reservation.isCancelled() );

        // verify freed capacity in the flight
        Reservation cancelled = service.find( reservationId, PASSWORD );
        Flight updatedFlight = cancelled.getFlight();

        assertEquals( updatedFlight.getCapacityLeft(), freeSpace + reservation.getCount() );
    }

    @Test( dependsOnMethods = "testCancel" )
    public void testCancelAlreadyCancelled() throws SecurityException {
        Reservation reservation = service.find( reservationId, PASSWORD );
        Flight flight = reservation.getFlight();
        int freeSpace = flight.getCapacityLeft();

        // verify assumption
        assertTrue( reservation.isCancelled() );

        // execute && verify
        assertTrue( service.cancel( reservationId, PASSWORD ).isCancelled() );

        // verify freed capacity in the flight
        Reservation cancelled = service.find( reservationId, PASSWORD );
        Flight updatedFlight = cancelled.getFlight();
        assertEquals( updatedFlight.getCapacityLeft(), freeSpace );
    }


    @Test
    public void testPrintConfirmation() {
        assertNotNull( service.printReservationConfirmation( reservationId, PASSWORD ) );
    }

    @Test
    public void testPrintConfirmation_WrongPassword() {
        try {
            service.printReservationConfirmation( reservationId, "wrongPassword" );
            fail( "Expected exception" );
        } catch ( SecurityException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof SecurityException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test
    public void testPrintConfirmation_Nonexistent() {
        try {
            service.printReservationConfirmation( 9999999999L, PASSWORD );
            fail( "Expected exception" );
        } catch ( NoSuchReservationException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof NoSuchReservationException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test( dependsOnMethods = "testCancel" )
    public void testPrintConfirmation_Cancelled() {
        try {
            service.printReservationConfirmation( reservationId, PASSWORD );
            fail( "Expected exception" );
        } catch ( ReservationCancelledException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof ReservationCancelledException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test( dependsOnMethods = "testCancel" )
    public void testPrintCancelConfirmation() {
        assertNotNull( service.printCancelConfirmation( reservationId, PASSWORD ) );
    }

    @Test( dependsOnMethods = "testCancel" )
    public void testPrintCancelConfirmation_WrongPassword() {
        try {
            service.printCancelConfirmation( reservationId, "wrongPassword" );
            fail( "Expected exception" );
        } catch ( SecurityException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof SecurityException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test
    public void testPrintCancelConfirmation_Nonexistent() {
        try {
            service.printCancelConfirmation( 9999999999L, PASSWORD );
            fail( "Expected exception" );
        } catch ( NoSuchReservationException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof NoSuchReservationException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test
    public void testPrintCancelConfirmation_NotCancelled() {
        try {
            service.printCancelConfirmation( reservationId, PASSWORD );
            fail( "Expected exception" );
        } catch ( ReservationNotCancelledException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof ReservationNotCancelledException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test( dependsOnMethods = "testPrintTicket_NotPaid" )
    public void testPrintTicket() throws Exception {

        // make assumption - pay it
        Reservation reservation = service.find( reservationId, PASSWORD );
        reservation.setPaid( reservation.getCost() );
        transaction.begin();
        em.merge( reservation );
        transaction.commit();

        // perform and verify
        assertNotNull( service.printETicket( reservationId, PASSWORD ) );
    }

    @Test( dependsOnMethods = "testPrintTicket" )
    public void testPrintTicket_WrongPassword() {
        try {
            service.printETicket( reservationId, "wrongPassword" );
            fail( "Expected exception" );
        } catch ( SecurityException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof SecurityException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test
    public void testPrintTicket_Nonexistent() {
        try {
            service.printETicket( 9999999999L, PASSWORD );
            fail( "Expected exception" );
        } catch ( NoSuchReservationException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof NoSuchReservationException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test
    public void testPrintTicket_NotPaid() {
        try {
            service.printETicket( reservationId, PASSWORD );
            fail( "Expected exception" );
        } catch ( ReservationNotPaidException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof ReservationNotPaidException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }

    @Test( dependsOnMethods = { "testCancel", "testPrintTicket" } )
    public void testPrintTicket_Cancelled() {
        try {
            service.printETicket( reservationId, PASSWORD );
            fail( "Expected exception" );
        } catch ( ReservationCancelledException ignored ) {
            // OK
        } catch ( EJBException ex ) {
            if ( ex.getCause() instanceof ReservationCancelledException ) {
                // OK
            } else {
                fail( "Wrong exception: " + ex.getCause().getClass() );
            }
        }
    }
}
