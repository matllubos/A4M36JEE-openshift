package cz.cvut.fel.aos.service.facade;

import cz.cvut.fel.aos.service.adapter.*;
import cz.cvut.fel.aos.service.adapter.SecurityException;
import cz.cvut.fel.aos.service.client.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.activation.DataHandler;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Date;

/** @author Karel Cemus */
@WebService( endpointInterface = "cz.cvut.fel.aos.service.facade.FacadeService" )
public class FacadeServiceImpl implements FacadeService {

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PrintService printService;

    @Override
    public Collection<Destination> findAllDestinations() {
        return destinationService.findAll();
    }

    @Override
    public Collection<Flight> findFlightsFrom( final Date intervalFrom, final Date intervalTo, final String codeFrom ) {
        return flightService.findFlightsFrom( intervalFrom, intervalTo, codeFrom );
    }

    @Override
    public Collection<Flight> findFlightsTo( final Date intervalFrom, final Date intervalTo, final String codeTo ) {
        return flightService.findFlightsTo( intervalFrom, intervalTo, codeTo );
    }

    @Override
    public Collection<Flight> findFlights( final Date intervalFrom, final Date intervalTo, final String codeFrom, final String codeTo ) {
        return flightService.findFlights( intervalFrom, intervalTo, codeFrom, codeTo );
    }

    @Override
    public Reservation findReservation( final long reservation, final String password ) throws SecurityException {
        return reservationService.find( reservation, password );
    }

    @Override
    public SuccessfulReservation createReservation( final String flightNumber, final String password, final int count ) throws FullFlightException {
        Reservation reservation = reservationService.create( flightNumber, password, count );
        Flight flight = reservation.getFlight();
        DataHandler confirmation = printService.printReservationConfirmation( reservation.getId(), password, flightNumber, flight.getFrom().getName(), flight.getTo().getName(), flight.getDeparture(), reservation.getCost() );
        return new SuccessfulReservation( reservation, confirmation );
    }

    @Override
    public DataHandler cancelReservation( final long reservationId, final String password ) throws SecurityException, NoSuchReservationException {
        Reservation reservation = reservationService.find( reservationId, password );

        // verify existence
        if ( reservation == null || reservation.isCanceled() ) throw new NoSuchReservationException( "No such reservation exists." );

        // cancel reservation
        reservationService.cancel( reservationId, password );

        Flight flight = reservation.getFlight();

        return printService.printCancelConfirmation( reservationId, flight.getNumber(), flight.getFrom().getName(), flight.getTo().getName(), flight.getDeparture() );
    }

    @Override
    public DataHandler payVisa( final long reservationId, final String password, final String cardName, final long creditCard, final Date validUntil, final int verificationCode ) throws SecurityException, InvalidPaymentException, NoSuchReservationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DataHandler payFromCanceledReservation( final long reservationIdFrom, final String passwordFrom, final long reservationIdTo, final String passwordTo ) throws SecurityException, NoSuchReservationException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DataHandler printTicket( final long reservation, final String password, final String flightNumber, final String destinationFrom, final String destinationTo, final Date takeOff ) throws NoSuchReservationException, SecurityException, ReservationNotPaidException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
