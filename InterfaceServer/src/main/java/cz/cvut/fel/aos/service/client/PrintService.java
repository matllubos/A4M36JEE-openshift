package cz.cvut.fel.aos.service.client;

import cz.cvut.fel.aos.service.print.PrintServiceImplService;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import java.util.Date;

/**
 * <p>Klient pro payment server</p>
 *
 * @author Karel Cemus
 */
@Service
public class PrintService {

    private final cz.cvut.fel.aos.service.print.PrintService client;

    public PrintService() {
        PrintServiceImplService factory = new PrintServiceImplService();
        client = factory.getPrintServiceImplPort();
    }

    public DataHandler printPaymentConfirmation( final long reservation, final long cardNumber, final int amount ) {
        return client.printPaymentConfirmation( reservation, cardNumber, amount );
    }

    public DataHandler printTicket( final long reservation, final String password, final String flightNumber, final String destinationFrom, final String destinationTo, final Date takeOff ) {
        return client.printTicket( reservation, password, flightNumber, destinationFrom, destinationTo, takeOff );
    }

    public DataHandler printCancelConfirmation( final long reservation, final String flightNumber, final String destinationFrom, final String destinationTo, final Date takeOff ) {
        return client.printCancelConfirmation( reservation, flightNumber, destinationFrom, destinationTo, takeOff );
    }

    public DataHandler printReservationConfirmation( final long reservation, final String password, final String flightNumber, final String destinationFrom, final String destinationTo, final Date takeOff, final int cost ) {
        return client.printReservationConfirmation( reservation, password, flightNumber, destinationFrom, destinationTo, takeOff, cost );
    }
}
