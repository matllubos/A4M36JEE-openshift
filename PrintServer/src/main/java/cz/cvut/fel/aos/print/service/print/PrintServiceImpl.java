package cz.cvut.fel.aos.print.service.print;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>Implementace služby pro tisk dokumentů</p>
 *
 * @author Karel Cemus
 */
@WebService( endpointInterface = "cz.cvut.fel.aos.print.service.print.PrintService" )
public class PrintServiceImpl implements PrintService {

    private static final String DELIMITER = "---------------------------------------------------------------------------\n";

    @Override
    public DataHandler printReservationConfirmation( final long reservation, final String password, final Date takeOff, final String destination, final String cost ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "Potvrzení o rezervaci\n" );
        builder.append( DELIMITER );
        builder.append( String.format( "%1$20s", "Číslo rezervace:" ) ).append( '\t' ).append( reservation ).append( '\n' );
        builder.append( String.format( "%1$20s", "Heslo pro přístup:" ) ).append( '\t' ).append( password ).append( '\n' );
        builder.append( String.format( "%1$20s", "Datum odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "dd.MM.yyyy" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( "%1$20s", "Čas odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "HH:mm" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( "%1$20s", "Destinace:" ) ).append( '\t' ).append( destination ).append( '\n' );
        builder.append( String.format( "%1$20s", "Cena:" ) ).append( '\t' ).append( cost ).append( " Kč" ).append( '\n' );
        builder.append( DELIMITER );

        return new DataHandler( new ByteArrayDataSource( builder.toString().getBytes(), "plain/text" ) );
    }

    @Override
    public DataHandler printTicket( final long reservation, final String password, final Date takeOff, final String destination ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "E-letenka\n" );
        builder.append( DELIMITER );
        builder.append( String.format( "%1$20s", "Číslo rezervace:" ) ).append( '\t' ).append( reservation ).append( '\n' );
        builder.append( String.format( "%1$20s", "Heslo pro přístup:" ) ).append( '\t' ).append( password ).append( '\n' );
        builder.append( String.format( "%1$20s", "Datum odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "dd.MM.yyyy" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( "%1$20s", "Čas odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "HH:mm" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( "%1$20s", "Destinace:" ) ).append( '\t' ).append( destination ).append( '\n' );
        builder.append( String.format( "%1$20s", "Stav:" ) ).append( '\t' ).append( "zaplaceno" ).append( '\n' );
        builder.append( DELIMITER );

        return new DataHandler( new ByteArrayDataSource( builder.toString().getBytes(), "plain/text" ) );
    }

    @Override
    public DataHandler printPaymentConfirmation( final long reservation, final long cardNumber, final int amount ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "Potvrzení o přijetí platby\n" );
        builder.append( DELIMITER );
        builder.append( String.format( "%1$20s", "Číslo rezervace:" ) ).append( '\t' ).append( reservation ).append( '\n' );
        builder.append( String.format( "%1$20s", "Kreditní karta:" ) ).append( '\t' ).append( cardNumber ).append( '\n' );
        builder.append( String.format( "%1$20s", "Přijatá částka:" ) ).append( '\t' ).append( amount ).append( '\n' );
        builder.append( String.format( "%1$20s", "Datum platby:" ) ).append( '\t' ).append( new SimpleDateFormat( "dd.MM.yyyy HH:mm" ).format( new Date() ) ).append( '\n' );
        builder.append( DELIMITER );

        return new DataHandler( new ByteArrayDataSource( builder.toString().getBytes(), "plain/text" ) );
    }

    @Override
    public DataHandler printCancelConfirmation( final long reservation, final Date takeOff, final String destination ) {
        final StringBuilder builder = new StringBuilder();

        builder.append( DELIMITER );
        builder.append( "Rezervace zrušena\n" );
        builder.append( DELIMITER );
        builder.append( String.format( "%1$20s", "Číslo rezervace:" ) ).append( '\t' ).append( reservation ).append( '\n' );
        builder.append( String.format( "%1$20s", "Datum odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "dd.MM.yyyy" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( "%1$20s", "Čas odletu:" ) ).append( '\t' ).append( new SimpleDateFormat( "HH:mm" ).format( takeOff ) ).append( '\n' );
        builder.append( String.format( "%1$20s", "Destinace:" ) ).append( '\t' ).append( destination ).append( '\n' );
        builder.append( DELIMITER );

        return new DataHandler( new ByteArrayDataSource( builder.toString().getBytes(), "plain/text" ) );
    }
}
