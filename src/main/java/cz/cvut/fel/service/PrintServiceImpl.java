package cz.cvut.fel.service;

import cz.cvut.fel.model.Flight;
import cz.cvut.fel.model.Payment;
import cz.cvut.fel.model.Reservation;

import javax.ejb.Stateless;
import java.text.SimpleDateFormat;

/** @author Karel Cemus */
@Stateless
public class PrintServiceImpl implements PrintService {

    private static final String DELIMITER = "---------------------------------------------------------------------------\n";

    private static final String FORMAT = "%1$20s\t";

    @Override
    public byte[] createConfirmation( final Reservation reservation, final String password ) {
        final Flight flight = reservation.getFlight();
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "Reservation confirmation\n" );
        builder.append( DELIMITER );
        builder.append( String.format( FORMAT, "Reservation number:" ) ).append( reservation.getId() ).append( '\n' );
        builder.append( String.format( FORMAT, "Password for access:" ) ).append( password ).append( '\n' );
        addCommonFlightInformation( builder, flight );
        builder.append( String.format( FORMAT, "Price:" ) ).append( reservation.getCost() ).append( " CZK" ).append( '\n' );
        builder.append( DELIMITER );

        return builder.toString().getBytes();
    }

    @Override
    public byte[] createConfirmation( final Payment payment ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "Payment confirmation\n" );
        builder.append( DELIMITER );
        builder.append( String.format( FORMAT, "Reservation number:" ) ).append( payment.getReservation().getId() ).append( '\n' );
        builder.append( String.format( FORMAT, "Credit card number:" ) ).append( String.format( "XXXX-XXXX-XXXX-%1$d", payment.getCreditCardNumber() ) ).append( '\n' );
        builder.append( String.format( FORMAT, "Amount received:" ) ).append( payment.getAmount() ).append( '\n' );
        builder.append( String.format( FORMAT, "Date of payment:" ) ).append( new SimpleDateFormat( "dd.MM.yyyy HH:mm:ss" ).format( payment.getTimestamp() ) ).append( '\n' );
        builder.append( DELIMITER );

        return builder.toString().getBytes();
    }

    @Override
    public byte[] createTicket( final Reservation reservation ) {
        final StringBuilder builder = new StringBuilder();
        final Flight flight = reservation.getFlight();

        builder.append( DELIMITER );
        builder.append( "E-ticket\n" );
        builder.append( DELIMITER );
        builder.append( String.format( FORMAT, "Reservation number:" ) ).append( reservation.getId() ).append( '\n' );
        addCommonFlightInformation( builder, flight );
        builder.append( String.format( FORMAT, "Status:" ) ).append( "paid" ).append( '\n' );
        builder.append( DELIMITER );

        return builder.toString().getBytes();
    }

    @Override
    public byte[] createCancelConfirmation( final Reservation reservation ) {
        final StringBuilder builder = new StringBuilder();
        final Flight flight = reservation.getFlight();

        builder.append( DELIMITER );
        builder.append( "Reservation cancelled\n" );
        builder.append( DELIMITER );
        builder.append( String.format( FORMAT, "Reservation number:" ) ).append( reservation.getId() ).append( '\n' );
        addCommonFlightInformation( builder, flight );
        builder.append( DELIMITER );

        return builder.toString().getBytes();
    }

    private void addCommonFlightInformation( final StringBuilder builder, final Flight flight ) {
        builder.append( String.format( FORMAT, "Flight number:" ) ).append( flight.getNumber() ).append( '\n' );
        builder.append( String.format( FORMAT, "Departure from:" ) ).append( flight.getFrom().getName() ).append( '\n' );
        builder.append( String.format( FORMAT, "Arrival to:" ) ).append( flight.getTo().getName() ).append( '\n' );
        builder.append( String.format( FORMAT, "Date of departure:" ) ).append( new SimpleDateFormat( "dd.MM.yyyy" ).format( flight.getDeparture().getScheduled() ) ).append( '\n' );
        builder.append( String.format( FORMAT, "Time of departure:" ) ).append( new SimpleDateFormat( "HH:mm" ).format( flight.getDeparture().getScheduled() ) ).append( '\n' );
    }

}
