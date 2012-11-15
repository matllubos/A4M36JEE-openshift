package cz.cvut.fel.aos.service.facade;

import cz.cvut.fel.aos.service.adapter.*;
import cz.cvut.fel.aos.service.adapter.SecurityException;

import javax.activation.DataHandler;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import java.util.Collection;
import java.util.Date;

/**
 * <p>Fasáda pro klienta. Služba zapouzdřuje přístup ke třetí vrstvě tvořené business servery.</p>
 *
 * @author Karel Cemus
 */
@WebService
public interface FacadeService {

    /** Vrací všechny destinace, do kterých společnost létá */
    Collection<Destination> findAllDestinations();

    /** všechny lety z dané oblasti v daném rozsahu dní */
    Collection<Flight> findFlightsFrom(
            @WebParam( name = "intervalFrom" ) Date intervalFrom,
            @WebParam( name = "intervalTo" ) Date intervalTo,
            @WebParam( name = "codeFrom" ) String codeFrom
    );

    /** všechny lety do dané oblasti v daném rozsahu dní */
    Collection<Flight> findFlightsTo(
            @WebParam( name = "intervalFrom" ) Date intervalFrom,
            @WebParam( name = "intervalTo" ) Date intervalTo,
            @WebParam( name = "codeTo" ) String codeTo
    );

    /** všechny lety mezi destinacemi v daném rozsahu dní */
    Collection<Flight> findFlights(
            @WebParam( name = "intervalFrom" ) Date intervalFrom,
            @WebParam( name = "intervalTo" ) Date intervalTo,
            @WebParam( name = "codeFrom" ) String codeFrom,
            @WebParam( name = "codeTo" ) String codeTo
    );

    /** najde rezervaci s daným číslem. Kontroluje oprávnění přístupu heslem v podobně SHA-1 */
    Reservation findReservation(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "password" ) String password
    ) throws SecurityException;


    /** vytvoří rezervaci na let pro daný počet míst. Nastaví i heslo pro přístup v podobě SHA-1 */
    SuccessfulReservation createReservation(
            @WebParam( name = "flightNumber" ) String flightNumber,
            @WebParam( name = "password" ) String password,
            @WebParam( name = "count" ) int count
    ) throws FullFlightException;


    /** zruší rezervaci, pokud má klient oprávnění. Pokud je již zrušeno, dojde k vygenerování výjimky */
    @XmlMimeType( "application/octet-stream" )
    DataHandler cancelReservation(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "password" ) String password
    ) throws SecurityException, NoSuchReservationException;


    /** přijme platbu visa kartou, vrací TXT potvrzení */
    @XmlMimeType( "application/octet-stream" )
    DataHandler payVisa(
            @WebParam( name = "reservationId" ) long reservationId,
            @WebParam( name = "password" ) String password,
            @WebParam( name = "cardName" ) String cardName,
            @WebParam( name = "creditCard" ) long creditCard,
            @WebParam( name = "validUntil" ) Date validUntil,
            @WebParam( name = "verificationCode" ) int verificationCode
    ) throws SecurityException, InvalidPaymentException, NoSuchReservationException;

    /** převod peněz ze zrušené rezervace na platnou, vrací TXT potvrzení */
    @XmlMimeType( "application/octet-stream" )
    boolean payFromCanceledReservation(
            @WebParam( name = "reservationIdFrom" ) long reservationIdFrom,
            @WebParam( name = "passwordFrom" ) String passwordFrom,
            @WebParam( name = "reservationIdTo" ) long reservationIdTo,
            @WebParam( name = "passwordTo" ) String passwordTo
    ) throws SecurityException, InvalidPaymentException, NoSuchReservationException;

    /** vytiskne e-letenku */
    @XmlMimeType( "application/octet-stream" )
    DataHandler printTicket(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "password" ) String password
    ) throws NoSuchReservationException, SecurityException, ReservationNotPaidException;
}
