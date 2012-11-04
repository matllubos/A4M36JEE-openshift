package cz.cvut.fel.aos.service.client;

import cz.cvut.fel.aos.service.adapter.Flight;
import cz.cvut.fel.aos.service.flight.FlightServiceImplService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Klient pro booking server</p>
 *
 * @author Karel Cemus
 */
@Service
public class FlightService {

    private final cz.cvut.fel.aos.service.flight.FlightService client;

    public FlightService() {
        FlightServiceImplService factory = new FlightServiceImplService();
        client = factory.getFlightServiceImplPort();
    }

    public Flight find( final String flightNumber ) {
        cz.cvut.fel.aos.service.flight.Flight flight = client.find( flightNumber );
        return flight == null ? null : new Flight( flight );
    }

    public List<Flight> findFlightsTo( final Date intervalFrom, final Date intervalTo, final String codeTo ) {
        List<cz.cvut.fel.aos.service.flight.Flight> flights = client.findFlightsTo( intervalFrom, intervalTo, codeTo );
        List<Flight> adapted = new ArrayList<Flight>();

        for ( cz.cvut.fel.aos.service.flight.Flight flight : flights )
            adapted.add( new Flight( flight ) );

        return adapted;
    }

    public List<Flight> findFlights( final Date intervalFrom, final Date intervalTo, final String codeFrom, final String codeTo ) {
        List<cz.cvut.fel.aos.service.flight.Flight> flights = client.findFlights( intervalFrom, intervalTo, codeFrom, codeTo );
        List<Flight> adapted = new ArrayList<Flight>();

        for ( cz.cvut.fel.aos.service.flight.Flight flight : flights )
            adapted.add( new Flight( flight ) );

        return adapted;
    }

    public List<Flight> findFlightsFrom( final Date intervalFrom, final Date intervalTo, final String codeFrom ) {
        List<cz.cvut.fel.aos.service.flight.Flight> flights = client.findFlightsFrom( intervalFrom, intervalTo, codeFrom );
        List<Flight> adapted = new ArrayList<Flight>();

        for ( cz.cvut.fel.aos.service.flight.Flight flight : flights )
            adapted.add( new Flight( flight ) );

        return adapted;
    }

    public List<Flight> findAllFlights() {
        List<cz.cvut.fel.aos.service.flight.Flight> flights = client.findAllFlights();
        List<Flight> adapted = new ArrayList<Flight>();

        for ( cz.cvut.fel.aos.service.flight.Flight flight : flights )
            adapted.add( new Flight( flight ) );

        return adapted;
    }
}
