package cz.cvut.fel.service;

import cz.cvut.fel.model.*;
import cz.cvut.fel.util.ArquillianTest;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static cz.cvut.fel.utils.DateUtils.date;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( groups = "services" )
public class PrintServiceTest extends ArquillianTest {

    @Inject
    private PrintService service;

    @Test
    public void testPrintPaymentConfirmation() throws IOException {

        Payment payment = new Payment();
        payment.setCreditCardName( "John Scott" );
        payment.setCreditCardNumber( 5478 );
        payment.setTimestamp( date( 1, 5, 2011, 11, 56 ) );
        payment.setAmount( 10000 );
        payment.setReservation( new Reservation() );
        payment.getReservation().setId( 123456 );

        byte[] confirmation = service.createConfirmation( payment );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Payment confirmation\n" +
                "---------------------------------------------------------------------------\n" +
                " Reservation number:\t123456\n" +
                " Credit card number:\tXXXX-XXXX-XXXX-5478\n" +
                "    Amount received:\t10000\n" +
                "    Date of payment:\t" + new SimpleDateFormat( "dd.MM.yyyy HH:mm:ss" ).format( payment.getTimestamp() ) + "\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( new String( confirmation ), expected );
    }

    @Test
    public void testPrintTicket() throws IOException {

        Reservation reservation = new Reservation();
        reservation.setId( 123456 );
        reservation.setFlight( new Flight() );
        reservation.getFlight().setNumber( "F213411" );
        reservation.getFlight().setFrom( new Destination() );
        reservation.getFlight().getFrom().setName( "Prague" );
        reservation.getFlight().setTo( new Destination() );
        reservation.getFlight().getTo().setName( "Brusel" );
        reservation.getFlight().setDeparture( new Time( date( 10, 12, 2012, 9, 50 ) ) );

        byte[] confirmation = service.createTicket( reservation );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "E-ticket\n" +
                "---------------------------------------------------------------------------\n" +
                " Reservation number:\t123456\n" +
                "      Flight number:\tF213411\n" +
                "     Departure from:\tPrague\n" +
                "         Arrival to:\tBrusel\n" +
                "  Date of departure:\t10.12.2012\n" +
                "  Time of departure:\t09:50\n" +
                "             Status:\tpaid\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( new String( confirmation ), expected );
    }

    @Test
    public void printReservationConfirmation() {

        Reservation reservation = new Reservation();
        reservation.setId( 123456 );
        reservation.setCost( 10000 );
        reservation.setFlight( new Flight() );
        reservation.getFlight().setNumber( "F213411" );
        reservation.getFlight().setFrom( new Destination() );
        reservation.getFlight().getFrom().setName( "Prague" );
        reservation.getFlight().setTo( new Destination() );
        reservation.getFlight().getTo().setName( "Madrid" );
        reservation.getFlight().setDeparture( new Time( date( 10, 12, 2012, 9, 50 ) ) );

        byte[] confirmation = service.createConfirmation( reservation, "somepassword" );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Reservation confirmation\n" +
                "---------------------------------------------------------------------------\n" +
                " Reservation number:\t123456\n" +
                "Password for access:\tsomepassword\n" +
                "      Flight number:\tF213411\n" +
                "     Departure from:\tPrague\n" +
                "         Arrival to:\tMadrid\n" +
                "  Date of departure:\t10.12.2012\n" +
                "  Time of departure:\t09:50\n" +
                "              Price:\t10000 CZK\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( new String( confirmation ), expected );
    }

    @Test
    public void printCancelConfirmation() {

        Reservation reservation = new Reservation();
        reservation.setId( 123456 );
        reservation.setCost( 10000 );
        reservation.setFlight( new Flight() );
        reservation.getFlight().setNumber( "F213411" );
        reservation.getFlight().setFrom( new Destination() );
        reservation.getFlight().getFrom().setName( "Prague" );
        reservation.getFlight().setTo( new Destination() );
        reservation.getFlight().getTo().setName( "London" );
        reservation.getFlight().setDeparture( new Time( date( 10, 12, 2012, 9, 50 ) ) );

        byte[] confirmation = service.createCancelConfirmation( reservation );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Reservation cancelled\n" +
                "---------------------------------------------------------------------------\n" +
                " Reservation number:\t123456\n" +
                "      Flight number:\tF213411\n" +
                "     Departure from:\tPrague\n" +
                "         Arrival to:\tLondon\n" +
                "  Date of departure:\t10.12.2012\n" +
                "  Time of departure:\t09:50\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( new String( confirmation ), expected );
    }
}
