package cz.cvut.fel.service;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.Reservation;
import cz.cvut.fel.exception.FullFlightException;
import cz.cvut.fel.service.reservation.ReservationService;
import cz.cvut.fel.utils.SecurityProvider;
import cz.cvut.fel.util.ArquillianTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
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

        Assert.assertEquals( reservation.getCount(), 5 );
        Assert.assertEquals( reservation.getPaid(), 0 );
        Assert.assertEquals( reservation.getFlight().getNumber(), "F987545" );
        Assert.assertEquals( reservation.getCost(), 5000 * 5 );
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
        Assert.assertEquals( reservation.getCount(), 5 );
        Assert.assertEquals( reservation.getFlight().getNumber(), "F987545" );
        Assert.assertEquals( reservation.getCost(), 5000 * 5 );
    }

    @Test( dependsOnMethods = "testCreate", expectedExceptions = { EJBException.class, SecurityException.class } )
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
        assertTrue( service.cancel( reservationId, PASSWORD ).isCanceled() );

        // verify freed capacity in the flight
        Reservation canceled = service.find( reservationId, PASSWORD );
        Flight updatedFlight = canceled.getFlight();

        Assert.assertEquals( updatedFlight.getCapacityLeft(), freeSpace + reservation.getCount() );
    }

    @Test( dependsOnMethods = "testCancel" )
    public void testCancelAlreadyCanceled() throws SecurityException {
        Reservation reservation = service.find( reservationId, PASSWORD );
        Flight flight = reservation.getFlight();
        int freeSpace = flight.getCapacityLeft();

        // verify assumption
        assertTrue( reservation.isCanceled() );

        // execute && verify
        assertTrue( service.cancel( reservationId, PASSWORD ).isCanceled() );

        // verify freed capacity in the flight
        Reservation canceled = service.find( reservationId, PASSWORD );
        Flight updatedFlight = canceled.getFlight();

        Assert.assertEquals( updatedFlight.getCapacityLeft(), freeSpace );
    }
}
