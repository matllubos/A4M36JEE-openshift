package cz.cvut.fel.service;

import cz.cvut.fel.model.Reservation;
import cz.cvut.fel.exception.InvalidPaymentException;
import cz.cvut.fel.service.payment.PaymentService;
import cz.cvut.fel.service.reservation.ReservationService;
import cz.cvut.fel.util.ArquillianTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.ejb.EJBException;
import javax.inject.Inject;

import static cz.cvut.fel.utils.DateUtils.date;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Slf4j
@Test( groups = "services" )
public class PaymentTest extends ArquillianTest {

    private static final String PASSWORD = "heslo12345";

    @Inject
    private PaymentService service;

    @Inject
    private ReservationService reservationService;

    private long reservationId;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        if ( isInContainer() ) {
            // data initialization
            Reservation reservation = reservationService.create( "F987545", PASSWORD, 5 );

            Assert.assertEquals( reservation.getCount(), 5 );
            reservationId = reservation.getId();
        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        if ( isInContainer() ) {
            reservationService.cancel( reservationId, PASSWORD );
        }
    }

    @Test
    public void testPayVisa() throws Exception {

        // verify assumptions - unpaid
        Reservation reservation = reservationService.find( reservationId, PASSWORD );
        Assert.assertEquals( reservation.getPaid(), 0 );

        // perform test
        reservation = service.payVisa( reservationId, PASSWORD, "John Scott", 123654789, date( 1, 1, 2014 ), 789 );

        // verify
        Assert.assertEquals( reservation.getPaid(), reservation.getCost() );
    }

    @Test( expectedExceptions = { EJBException.class, SecurityException.class } )
    public void testPayVisaWrongPassword() throws Exception {

        // perform test
        service.payVisa( reservationId, "wrong password", "John Scott", 123654789, date( 1, 1, 2014 ), 789 );

        fail( "Expected exception" );
    }

    @Test( expectedExceptions = { EJBException.class, InvalidPaymentException.class } )
    public void testPayVisaWrongVerification() throws Exception {

        // perform test
        service.payVisa( reservationId, PASSWORD, "John Scott", 123654789, date( 1, 1, 2014 ), 123 );

        fail( "Expected exception" );
    }

    @Test( expectedExceptions = { EJBException.class, InvalidPaymentException.class } )
    public void testPayVisaExpired() throws Exception {

        // perform test
        service.payVisa( reservationId, PASSWORD, "John Scott", 123654789, date( 1, 1, 2010 ), 789 );

        fail( "Expected exception" );
    }

    @Test( expectedExceptions = { EJBException.class, InvalidPaymentException.class } )
    public void testPayVisaCanceled() throws Exception {

        // assumptions
        reservationService.cancel( reservationId, PASSWORD );
        Reservation reservation = reservationService.find( reservationId, PASSWORD );
        assertTrue( reservation.isCanceled() );

        // perform test
        service.payVisa( reservationId, PASSWORD, "John Scott", 123654789, date( 1, 1, 2014 ), 789 );

        fail( "Expected exception" );
    }

    @Test
    public void testTransfer() throws Exception {

        // verify assumption - paid
        service.payVisa( reservationId, PASSWORD, "John Scott", 123654789, date( 1, 1, 2014 ), 789 );
        reservationService.cancel( reservationId, PASSWORD );
        Reservation reservationFrom = reservationService.find( reservationId, PASSWORD );

        Assert.assertEquals( reservationFrom.getPaid(), reservationFrom.getCost() );
        assertTrue( reservationFrom.isCanceled() );

        // create new
        Reservation reservationTo = reservationService.create( "F987545", PASSWORD, 4 );

        try {
            // assumption
            Assert.assertEquals( reservationTo.getPaid(), 0 );

            // perform test
            reservationTo = service.transferFromReservation( reservationFrom.getId(), PASSWORD, reservationTo.getId(), PASSWORD );
            reservationFrom = reservationService.find( reservationId, PASSWORD );

            // verify
            Assert.assertEquals( reservationTo.getPaid(), reservationTo.getCost() );
            Assert.assertEquals( reservationFrom.getPaid(), reservationFrom.getFlight().getCost() ); // cena 1 místa

        } finally {
            // clean up reservation
            reservationService.cancel( reservationTo.getId(), PASSWORD );
        }
    }
}
