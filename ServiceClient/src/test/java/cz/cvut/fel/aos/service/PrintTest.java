package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.print.service.print.PrintService;
import cz.cvut.fel.aos.print.service.print.PrintServiceImplService;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.activation.DataHandler;
import java.io.IOException;

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
        System.out.println(data);
    }

    @Test
    public void printTicket() {

    }

    @Test
    public void printReservationConfirmation() {

    }

    @Test
    public void printCancelConfirmation() {

    }
}
