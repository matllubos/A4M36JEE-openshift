package cz.cvut.fel.aos.service.facade;

import cz.cvut.fel.aos.model.Destination;
import cz.cvut.fel.aos.model.Flight;
import cz.cvut.fel.aos.model.Reservation;
import cz.cvut.fel.aos.service.destination.DestinationService;
import cz.cvut.fel.aos.service.flight.FlightService;
import cz.cvut.fel.aos.service.payment.InvalidPaymentException;
import cz.cvut.fel.aos.service.payment.NoSuchReservationException;
import cz.cvut.fel.aos.service.payment.PaymentService;
import cz.cvut.fel.aos.service.payment.SecurityException;
import cz.cvut.fel.aos.service.print.PrintService;
import cz.cvut.fel.aos.service.reservation.FullFlightException;
import cz.cvut.fel.aos.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.jws.WebService;
import java.util.Collection;
import java.util.Date;

/** @author Karel Cemus */
//@WebService( endpointInterface = "cz.cvut.fel.aos.service.facade.FacadeService" )
@Service
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
        return destinationService.findAllDestinations();
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
        if ( reservation == null || reservation.isCanceled() ) {
            throw new NoSuchReservationException( "No such reservation exists." );
        }

        // cancel reservation
        reservationService.cancel( reservationId, password );

        Flight flight = reservation.getFlight();

        return printService.printCancelConfirmation( reservationId, flight.getNumber(), flight.getFrom().getName(), flight.getTo().getName(), flight.getDeparture() );
    }

    @Override
    public DataHandler payVisa( final long reservationId, final String password, final String cardName, final long creditCard, final Date validUntil, final int verificationCode ) throws SecurityException, InvalidPaymentException, NoSuchReservationException {
        // get paid status before paying
        Reservation before = reservationService.find( reservationId, password );

        // get paid status after paying
        Reservation after = paymentService.payVisa( reservationId, password, cardName, creditCard, validUntil, verificationCode );

        // print confirmation
        return printService.printPaymentConfirmation( reservationId, creditCard, after.getPaid() - before.getPaid() );
    }

    @Override
    public boolean payFromCanceledReservation( final long reservationIdFrom, final String passwordFrom, final long reservationIdTo, final String passwordTo ) throws SecurityException, NoSuchReservationException, InvalidPaymentException {
        paymentService.transferFromReservation( reservationIdFrom, passwordFrom, reservationIdTo, passwordTo );

        // no exception, payment was successful
        return true;
    }

    @Override
    public DataHandler printTicket( final long reservationId, final String password ) throws NoSuchReservationException, SecurityException, ReservationNotPaidException {
        // verify paid status
        Reservation reservation = reservationService.find( reservationId, password );

        if ( reservation == null ) throw new NoSuchReservationException( "Reservation doesn't exists." );
        if ( reservation.isCanceled() ) throw new NoSuchReservationException( "Reservation is canceled." );
        if ( reservation.getPaid() != reservation.getCost() ) {
            throw new ReservationNotPaidException( "Reservation is not paid, e-ticket cannot be provided." );
        }

        Flight flight = reservation.getFlight();

        return printService.printTicket( reservationId, flight.getNumber(), flight.getFrom().getName(), flight.getTo().getName(), flight.getDeparture() );
    }
}
