package cz.cvut.fel.aos.bookingserver.service;

import cz.cvut.fel.aos.bookingserver.model.Flight;
import java.util.Collection;
import java.util.Date;
import javax.jws.WebService;

/**
 * API poskytující seznamy letů
 *
 * @author Karel Cemus
 */
@WebService
public interface FlightService {

    /** najde let s daným číslem */
    Flight find( String flightNumber );
    
    /** Vrací všechny lety, které jsou zavedené v systému společnosti */
    Collection<Flight> findAll();
    
    /** všechny lety z dané oblasti v daném rozsahu dní */
    Collection<Flight> findFlightsFrom( Date intervalFrom, Date intervalTo, String codeFrom );
    
    /** všechny lety do dané oblasti v daném rozsahu dní */
    Collection<Flight> findFlightsTo( Date intervalFrom, Date intervalTo, String codeTo );
    
    /** všechny lety mezi destinacemi v daném rozsahu dní */
    Collection<Flight> findFlights( Date intervalFrom, Date intervalTo, String codeFrom, String codeTo );
}
