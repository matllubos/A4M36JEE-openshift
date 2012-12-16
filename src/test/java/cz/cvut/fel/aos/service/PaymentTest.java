package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.model.Reservation;
import cz.cvut.fel.aos.service.payment.InvalidPaymentException;
import cz.cvut.fel.aos.service.payment.NoSuchReservationException;
import cz.cvut.fel.aos.service.payment.PaymentService;
import cz.cvut.fel.aos.service.payment.SecurityException;
import cz.cvut.fel.aos.service.reservation.FullFlightException;
import cz.cvut.fel.aos.service.reservation.ReservationService;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static cz.cvut.fel.aos.utils.DateUtils.date;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = false )
public class PaymentTest {

    private static final String PASSWORD = "heslo12345";

    PaymentService service;

    ReservationService reservationService;

    private long reservationId;

    @BeforeTest
    public void setUp() {
        //        ReservationServiceImplService reservationFactory = new ReservationServiceImplService( ReservationServiceImplService.WSDL_LOCATION, ReservationServiceImplService.SERVICE );
        //        reservationService = reservationFactory.getReservationServiceImplPort();
        //
        //        PaymentServiceImplService factory = new PaymentServiceImplService( PaymentServiceImplService.WSDL_LOCATION, PaymentServiceImplService.SERVICE );
        //        service = factory.getPaymentServiceImplPort();
    }

    @BeforeMethod
    public void setUpMethod() throws FullFlightException {
        // inicializace dat pro testy
        Reservation reservation = reservationService.create( "F987545", PASSWORD, 5 );

        Assert.assertEquals( reservation.getCount(), 5 );
        reservationId = reservation.getId();
    }

    @AfterMethod
    public void tearDown() throws cz.cvut.fel.aos.service.reservation.SecurityException {
        reservationService.cancel( reservationId, PASSWORD );
    }

    @Test
    public void testPayVisa() throws SecurityException, NoSuchReservationException, InvalidPaymentException, SecurityException, cz.cvut.fel.aos.service.reservation.SecurityException {

        // ověř předpoklady - nezaplaceno
        Reservation reservation = reservationService.find( reservationId, PASSWORD );
        Assert.assertEquals( reservation.getPaid(), 0 );

        // perform test
        reservation = service.payVisa( reservationId, PASSWORD, "Jan Novak", 123654789, date( 1, 1, 2014 ), 789 );

        // verify
        Assert.assertEquals( reservation.getPaid(), reservation.getCost() );
    }

    @Test( expectedExceptions = SecurityException.class )
    public void testPayVisaWrongPassword() throws SecurityException, NoSuchReservationException, InvalidPaymentException {

        // perform test
        service.payVisa( reservationId, "wrong password", "Jan Novak", 123654789, date( 1, 1, 2014 ), 789 );

        fail( "Expected exception" );
    }

    @Test( expectedExceptions = InvalidPaymentException.class )
    public void testPayVisaWrongVerification() throws SecurityException, NoSuchReservationException, InvalidPaymentException {

        // perform test
        service.payVisa( reservationId, PASSWORD, "Jan Novak", 123654789, date( 1, 1, 2014 ), 123 );

        fail( "Expected exception" );
    }

    @Test( expectedExceptions = InvalidPaymentException.class )
    public void testPayVisaExpired() throws SecurityException, NoSuchReservationException, InvalidPaymentException {

        // perform test
        service.payVisa( reservationId, PASSWORD, "Jan Novak", 123654789, date( 1, 1, 2010 ), 789 );

        fail( "Expected exception" );
    }

    @Test( expectedExceptions = InvalidPaymentException.class )
    public void testPayVisaCanceled() throws SecurityException, NoSuchReservationException, InvalidPaymentException, cz.cvut.fel.aos.service.reservation.SecurityException {

        // předpoklady
        reservationService.cancel( reservationId, PASSWORD );
        Reservation reservation = reservationService.find( reservationId, PASSWORD );
        assertTrue( reservation.isCanceled() );

        // perform test
        service.payVisa( reservationId, PASSWORD, "Jan Novak", 123654789, date( 1, 1, 2014 ), 789 );

        fail( "Expected exception" );
    }

    @Test
    public void testTransfer() throws SecurityException, NoSuchReservationException, InvalidPaymentException, FullFlightException, cz.cvut.fel.aos.service.reservation.SecurityException {

        // ověř předpoklady - zaplaceno
        service.payVisa( reservationId, PASSWORD, "Jan Novak", 123654789, date( 1, 1, 2014 ), 789 );
        reservationService.cancel( reservationId, PASSWORD );
        Reservation reservationFrom = reservationService.find( reservationId, PASSWORD );

        Assert.assertEquals( reservationFrom.getPaid(), reservationFrom.getCost() );
        assertTrue( reservationFrom.isCanceled() );

        // založ novou
        Reservation reservationTo = reservationService.create( "F987545", PASSWORD, 4 );

        try {
            // předpoklad
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
