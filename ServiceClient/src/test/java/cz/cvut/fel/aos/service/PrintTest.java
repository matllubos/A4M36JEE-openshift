package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.service.print.PrintService;
import cz.cvut.fel.aos.service.print.PrintServiceImplService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.activation.DataHandler;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static cz.cvut.fel.aos.utils.DateUtils.date;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = true )
public class PrintTest {

    private PrintService service;

    @BeforeTest
    public void setUp() {

        PrintServiceImplService factory = new PrintServiceImplService( PrintServiceImplService.WSDL_LOCATION, PrintServiceImplService.SERVICE );
        service = factory.getPrintServiceImplPort();
    }

    @Test
    public void testPrintPaymentConfirmation() throws IOException {

        DataHandler handler = service.printPaymentConfirmation( 123456, 987654321, 10000 );

        byte[] buffer = new byte[ 1000000 ];
        int read = handler.getInputStream().read( buffer );

        String data = new String( buffer, 0, read );
        System.out.println( data );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Potvrzení o přijetí platby\n" +
                "---------------------------------------------------------------------------\n" +
                "    Číslo rezervace:\t123456\n" +
                "     Kreditní karta:\t987654321\n" +
                "     Přijatá částka:\t10000\n" +
                "       Datum platby:\t" + new SimpleDateFormat( "dd.MM.yyyy HH:mm" ).format( new Date() ) + "\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( data, expected );
    }

    @Test
    public void printTicket() throws IOException {

        DataHandler handler = service.printTicket( 123456, "somepassword", 21341, "Prague", "Brusel", date( 10, 12, 2012, 9, 50 ) );

        byte[] buffer = new byte[ 1000000 ];
        int read = handler.getInputStream().read( buffer );

        String data = new String( buffer, 0, read );
        System.out.println( data );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "E-letenka\n" +
                "---------------------------------------------------------------------------\n" +
                "    Číslo rezervace:\t123456\n" +
                "  Heslo pro přístup:\tsomepassword\n" +
                "         Číslo letu:\t21341\n" +
                "            Odlet z:\tPrague\n" +
                "          Přílet do:\tBrusel\n" +
                "       Datum odletu:\t10.12.2012\n" +
                "         Čas odletu:\t09:50\n" +
                "               Stav:\tzaplaceno\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( data, expected );
    }

    @Test
    public void printReservationConfirmation() throws IOException {

        DataHandler handler = service.printReservationConfirmation( 123456, "somepassword", 31248, "Prague", "Madrid", date( 10, 12, 2012, 9, 50 ), 10000 );

        byte[] buffer = new byte[ 1000000 ];
        int read = handler.getInputStream().read( buffer );

        String data = new String( buffer, 0, read );
        System.out.println( data );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Potvrzení o rezervaci\n" +
                "---------------------------------------------------------------------------\n" +
                "    Číslo rezervace:\t123456\n" +
                "  Heslo pro přístup:\tsomepassword\n" +
                "         Číslo letu:\t31248\n" +
                "            Odlet z:\tPrague\n" +
                "          Přílet do:\tMadrid\n" +
                "       Datum odletu:\t10.12.2012\n" +
                "         Čas odletu:\t09:50\n" +
                "               Cena:\t10000 Kč\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( data, expected );
    }

    @Test
    public void printCancelConfirmation() throws IOException {

        DataHandler handler = service.printCancelConfirmation( 123456, 354789, "Prague", "London", date( 10, 12, 2012, 9, 50 ) );

        byte[] buffer = new byte[ 1000000 ];
        int read = handler.getInputStream().read( buffer );

        String data = new String( buffer, 0, read );
        System.out.println( data );

        String expected = "" +
                "---------------------------------------------------------------------------\n" +
                "Rezervace zrušena\n" +
                "---------------------------------------------------------------------------\n" +
                "    Číslo rezervace:\t123456\n" +
                "         Číslo letu:\t354789\n" +
                "            Odlet z:\tPrague\n" +
                "          Přílet do:\tLondon\n" +
                "       Datum odletu:\t10.12.2012\n" +
                "         Čas odletu:\t09:50\n" +
                "---------------------------------------------------------------------------\n";

        assertEquals( data, expected );
    }
}
