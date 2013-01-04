package cz.cvut.fel.service;

import cz.cvut.fel.exception.*;
import cz.cvut.fel.model.Reservation;

import java.io.Serializable;

/**
 * Service provides the interface to reservation administration. Through this API can be created, found and cancelled reservations
 * in the booking system.
 *
 * @author Karel Cemus
 */
public interface ReservationService extends Serializable {

    /** finds the reservation with given number and verifies the access validity with given password. */
    Reservation find( long reservation, String password ) throws SecurityException, NoSuchReservationException;

    /**
     * creates new reservation in the system. It tries to book given number of seats in given flight. Reservation will be
     * protected by password
     */
    Reservation create( String flightNumber, String password, int seats ) throws FullFlightException, NoSuchFlightException;

    /** it passwords matches the reservation will be cancelled */
    Reservation cancel( long reservation, String password ) throws SecurityException;

    /** prints confirmation that given reservation exists and is active */
    byte[] printReservationConfirmation( long reservation, String password ) throws NoSuchReservationException, ReservationCancelledException, SecurityException;

    /** prints confirmation that given reservation has been cancelled */
    byte[] printCancelConfirmation( long reservation, String password ) throws NoSuchReservationException, ReservationNotCancelledException, SecurityException;

    /** prints e-ticket for paid reservation */
    byte[] printETicket( long reservation, String password ) throws NoSuchReservationException, ReservationNotPaidException, SecurityException, ReservationCancelledException;
}
