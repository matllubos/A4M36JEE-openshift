package cz.cvut.fel.aos.service.client;

import cz.cvut.fel.aos.service.adapter.FullFlightException;
import cz.cvut.fel.aos.service.adapter.Reservation;
import cz.cvut.fel.aos.service.adapter.SecurityException;
import cz.cvut.fel.aos.service.reservation.ReservationServiceImplService;
import org.springframework.stereotype.Service;

/**
 * <p>Klient pro booking server</p>
 *
 * @author Karel Cemus
 */
@Service
public class ReservationService {

    private final cz.cvut.fel.aos.service.reservation.ReservationService client;

    public ReservationService() {
        ReservationServiceImplService factory = new ReservationServiceImplService();
        client = factory.getReservationServiceImplPort();
    }

    public Reservation find( final long reservation, final String password ) throws SecurityException {
        try {
            cz.cvut.fel.aos.service.reservation.Reservation instance = client.find( reservation, password );
            return instance == null ? null : new Reservation( instance );
        } catch ( cz.cvut.fel.aos.service.reservation.SecurityException e ) {
            throw new SecurityException( e.getMessage(), e.getCause() );
        }
    }

    public Reservation create( final String flightNumber, final String password, final int count ) throws FullFlightException {
        try {
            return new Reservation( client.create( flightNumber, password, count ) );
        } catch ( cz.cvut.fel.aos.service.reservation.FullFlightException e ) {
            throw new FullFlightException( e.getMessage(), e.getCause() );
        }
    }

    public boolean cancel( final long reservation, final String password ) throws SecurityException {
        try {
            return client.cancel( reservation, password );
        } catch ( cz.cvut.fel.aos.service.reservation.SecurityException e ) {
            throw new SecurityException( e.getMessage(), e.getCause() );
        }
    }

}
