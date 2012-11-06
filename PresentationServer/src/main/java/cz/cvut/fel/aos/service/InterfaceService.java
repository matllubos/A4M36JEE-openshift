package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.service.facade.*;
import cz.cvut.fel.aos.service.facade.SecurityException;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import java.util.Date;
import java.util.List;

/**
 * <p>Wrapper pro WS klienta poskytujícího přístup k serveru 2nd vrstvy.</p>
 *
 * @author Karel Cemus
 */
@Service
public class InterfaceService {

    private FacadeService client;

    public InterfaceService() {
        FacadeServiceImplService factory = new FacadeServiceImplService();
        client = factory.getFacadeServiceImplPort();
    }


    public DataHandler cancelReservation( final long reservation, final String password ) throws NoSuchReservationException, SecurityException {
        return client.cancelReservation( reservation, password );
    }


    public Reservation findReservation( final long reservation, final String password ) throws SecurityException {
        return client.findReservation( reservation, password );
    }


    public DataHandler printTicket( final long reservation, final String password ) throws NoSuchReservationException, SecurityException, ReservationNotPaidException {
        return client.printTicket( reservation, password );
    }


    public SuccessfulReservation createReservation( final String flightNumber, final String password, final int count ) throws FullFlightException {
        return client.createReservation( flightNumber, password, count );
    }


    public List<Flight> findFlightsFrom( final Date intervalFrom, final Date intervalTo, final String codeFrom ) {
        return client.findFlightsFrom( intervalFrom, intervalTo, codeFrom );
    }


    public List<Destination> findAllDestinations() {
        return client.findAllDestinations();
    }


    public boolean payFromCanceledReservation( final long reservationIdFrom, final String passwordFrom, final long reservationIdTo, final String passwordTo ) throws NoSuchReservationException, SecurityException, InvalidPaymentException {
        return client.payFromCanceledReservation( reservationIdFrom, passwordFrom, reservationIdTo, passwordTo );
    }


    public List<Flight> findFlightsTo( final Date intervalFrom, final Date intervalTo, final String codeTo ) {
        return client.findFlightsTo( intervalFrom, intervalTo, codeTo );
    }


    public List<Flight> findFlights( final Date intervalFrom, final Date intervalTo, final String codeFrom, final String codeTo ) {
        return client.findFlights( intervalFrom, intervalTo, codeFrom, codeTo );
    }


    public DataHandler payVisa( final long reservationId, final String password, final String cardName, final long creditCard, final Date validUntil, final int verificationCode ) throws NoSuchReservationException, SecurityException, InvalidPaymentException {
        return client.payVisa( reservationId, password, cardName, creditCard, validUntil, verificationCode );
    }
}
