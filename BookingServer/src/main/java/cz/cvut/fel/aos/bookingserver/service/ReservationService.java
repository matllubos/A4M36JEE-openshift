package cz.cvut.fel.aos.bookingserver.service;

import cz.cvut.fel.aos.bookingserver.model.Reservation;

import javax.jws.WebService;

/**
 * API pro přístup k rezervacím. Umožňuje jejich tvorbu a aktualizaci
 *
 * @author Karel Cemus
 */
@WebService
public interface ReservationService {

    /** najde rezervaci s daým číslem. Kontroluje oprávnění přístupu heslem v podobně SHA-1 */
    Reservation find( long reservation, String password ) throws SecurityException;

    /** vytvoří rezervaci na let pro daný počet míst. Nastaví i heslo pro přístup v podobě SHA-1 */
    Reservation create( String flightNumber, String password, int count ) throws IllegalArgumentException;

    /** zruší rezervaci, pokud má klient oprávnění. Heslo v podobně SHA-1 */
    boolean cancel( long reservation, String password ) throws SecurityException;

    /** přijme platbu pro danou rezervaci. Heslo v podobě SHA-1 */
    Reservation pay( long reservation, String password, int amount ) throws SecurityException;

    /** vybere peníze vložené na zrušenou rezervaci. Lze použít pro platbu jiné rezervace */
    int withdrawCredit( long reservation, String password, int amount ) throws SecurityException;
}
