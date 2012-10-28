package cz.cvut.fel.aos.print.service.print;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import java.util.Date;

/**
 * <p>Služba poskytující rozhraní pro tisk potvrzení o provedení dané operace</p>
 *
 * @author Karel Cemus
 */
@WebService
public interface PrintService {

    /** vytiskne potvrzení o vytvoření rezervace */
    @XmlMimeType( "application/octet-stream" )
    DataHandler printReservationConfirmation(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "password" ) String password,
            @WebParam( name = "takeOff" ) Date takeOff,
            @WebParam( name = "destination" ) String destination,
            @WebParam( name = "cost" ) String cost
    );

    /** vytiskne e-letenku */
    @XmlMimeType( "application/octet-stream" )
    DataHandler printTicket(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "password" ) String password,
            @WebParam( name = "takeOff" ) Date takeOff,
            @WebParam( name = "destination" ) String destination
    );


    /** vytiskne potvrzení o přijetí platby */
    @XmlMimeType( "application/octet-stream" )
    DataHandler printPaymentConfirmation(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "cardNumber" ) long cardNumber,
            @WebParam( name = "amount" ) int amount
    );


    /** vytiskne potvrzení o zrušení rezervace */
    @XmlMimeType( "application/octet-stream" )
    DataHandler printCancelConfirmation(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "takeOff" ) Date takeOff,
            @WebParam( name = "destination" ) String destination
    );

}
