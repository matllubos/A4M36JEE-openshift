package cz.cvut.fel.aos.service.print;

import javax.activation.DataHandler;
import javax.ejb.Stateless;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Implementace služby pro tisk dokumentů</p>
 *
 * @author Karel Cemus
 */
@Stateless
@Deprecated
public class PrintServiceImpl implements PrintService {

    private static final String DELIMITER = "---------------------------------------------------------------------------\n";

    private static final String FORMAT = "%1$20s";

    @Override
    public DataHandler printReservationConfirmation( final long reservation, final String password, final String flightNumber, final String destinationFrom, final String destinationTo, final Date takeOff, final int cost ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "Potvrzení o rezervaci\n" );
        builder.append( DELIMITER );
        builder.append( String.format( FORMAT, "Číslo rezervace:" ) ).append( '\t' ).append( reservation ).append( '\n' );
        builder.append( String.format( FORMAT, "Heslo pro přístup:" ) ).append( '\t' ).append( password ).append( '\n' );
        builder.append( String.format( FORMAT, "Číslo letu:" ) ).append( '\t' ).append( flightNumber ).append( '\n' );
        builder.append( String.format( FORMAT, "Odlet z:" ) ).append( '\t' ).append( destinationFrom ).append( '\n' );
        builder.append( String.format( FORMAT, "Přílet do:" ) ).append( '\t' ).append( destinationTo ).append( '\n' );
        builder.append( String.format( FORMAT, "Datum odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "dd.MM.yyyy" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( FORMAT, "Čas odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "HH:mm" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( FORMAT, "Cena:" ) ).append( '\t' ).append( cost ).append( " Kč" ).append( '\n' );
        builder.append( DELIMITER );

//        return new DataHandler( new ByteArrayDataSource( builder.toString().getBytes(), "plain/text" ) );
        return null;
    }

    @Override
    public DataHandler printTicket( final long reservation, final String flightNumber, final String destinationFrom, final String destinationTo, final Date takeOff ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "E-letenka\n" );
        builder.append( DELIMITER );
        builder.append( String.format( FORMAT, "Číslo rezervace:" ) ).append( '\t' ).append( reservation ).append( '\n' );
        builder.append( String.format( FORMAT, "Číslo letu:" ) ).append( '\t' ).append( flightNumber ).append( '\n' );
        builder.append( String.format( FORMAT, "Odlet z:" ) ).append( '\t' ).append( destinationFrom ).append( '\n' );
        builder.append( String.format( FORMAT, "Přílet do:" ) ).append( '\t' ).append( destinationTo ).append( '\n' );
        builder.append( String.format( FORMAT, "Datum odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "dd.MM.yyyy" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( FORMAT, "Čas odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "HH:mm" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( FORMAT, "Stav:" ) ).append( '\t' ).append( "zaplaceno" ).append( '\n' );
        builder.append( DELIMITER );

//        return new DataHandler( new ByteArrayDataSource( builder.toString().getBytes(), "plain/text" ) );
        return null;
    }

    @Override
    public DataHandler printCancelConfirmation( final long reservation, final String flightNumber, final String destinationFrom, final String destinationTo, final Date takeOff ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "Rezervace zrušena\n" );
        builder.append( DELIMITER );
        builder.append( String.format( FORMAT, "Číslo rezervace:" ) ).append( '\t' ).append( reservation ).append( '\n' );
        builder.append( String.format( FORMAT, "Číslo letu:" ) ).append( '\t' ).append( flightNumber ).append( '\n' );
        builder.append( String.format( FORMAT, "Odlet z:" ) ).append( '\t' ).append( destinationFrom ).append( '\n' );
        builder.append( String.format( FORMAT, "Přílet do:" ) ).append( '\t' ).append( destinationTo ).append( '\n' );
        builder.append( String.format( FORMAT, "Datum odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "dd.MM.yyyy" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( FORMAT, "Čas odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "HH:mm" ).format( takeOff ) ).append( '\n' );
        builder.append( DELIMITER );

//     return new DataHandler( new ByteArrayDataSource( builder.toString().getBytes(), "plain/text" ) );
        return null;
    }


    @Override
    public DataHandler printPaymentConfirmation( final long reservation, final long cardNumber, final int amount ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "Potvrzení o přijetí platby\n" );
        builder.append( DELIMITER );
        builder.append( String.format( FORMAT, "Číslo rezervace:" ) ).append( '\t' ).append( reservation ).append( '\n' );
        builder.append( String.format( FORMAT, "Kreditní karta:" ) ).append( '\t' ).append( cardNumber ).append( '\n' );
        builder.append( String.format( FORMAT, "Přijatá částka:" ) ).append( '\t' ).append( amount ).append( '\n' );
        builder.append( String.format( FORMAT, "Datum platby:" ) ).append( '\t' ).append( new SimpleDateFormat( "dd.MM.yyyy HH:mm" ).format( new Date() ) ).append( '\n' );
        builder.append( DELIMITER );

//        return new DataHandler( new ByteArrayDataSource( builder.toString().getBytes(), "plain/text" ) );
        return null;
    }

}
