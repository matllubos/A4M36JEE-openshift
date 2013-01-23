package cz.cvut.fel.service;

import cz.cvut.fel.exception.NoSuchFlightException;
import cz.cvut.fel.model.Flight;

import java.util.Date;
import java.util.List;

/** @author Karel Cemus */
public interface FlightService {

    /** returns flight with the particular number */
    Flight find( String flightNumber ) throws NoSuchFlightException;

    /** all flights into given destination */
    List<Flight> findFlightsFrom( Date intervalFrom, Date intervalTo, String codeFrom );

    /** all flights from given destination */
    List<Flight> findFlightsTo( Date intervalFrom, Date intervalTo, String codeTo );

    /** all flights between given destinations */
    List<Flight> findFlights( Date intervalFrom, Date intervalTo, String codeFrom, String codeTo );

    /** saves given flight whether it is new or modified only */
    Flight save( Flight flight );

    /** deletes the selected destination and it will not be available any more */
    void delete( String number ) throws NoSuchFlightException;
}
