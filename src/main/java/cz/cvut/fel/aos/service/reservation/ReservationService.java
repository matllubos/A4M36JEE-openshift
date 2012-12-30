package cz.cvut.fel.aos.service.reservation;

import cz.cvut.fel.aos.model.Reservation;
import cz.cvut.fel.exception.FullFlightException;

import javax.jws.WebParam;

/**
 * API pro přístup k rezervacím. Umožňuje jejich tvorbu a aktualizaci
 *
 * @author Karel Cemus
 */
public interface ReservationService {

    /** najde rezervaci s daným číslem. Kontroluje oprávnění přístupu heslem */
    Reservation find(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "password" ) String password
    ) throws SecurityException;


    /** vytvoří rezervaci na let pro daný počet míst. Nastaví i heslo pro přístup */
    Reservation create(
            @WebParam( name = "flightNumber" ) String flightNumber,
            @WebParam( name = "password" ) String password,
            @WebParam( name = "count" ) int count
    ) throws FullFlightException;


    /** zruší rezervaci, pokud má klient oprávnění. */
    Reservation cancel(
            @WebParam( name = "reservation" ) long reservation,
            @WebParam( name = "password" ) String password
    ) throws SecurityException;
}
