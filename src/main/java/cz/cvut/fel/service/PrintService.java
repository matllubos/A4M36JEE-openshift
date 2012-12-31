package cz.cvut.fel.service;

import cz.cvut.fel.model.Payment;
import cz.cvut.fel.model.Reservation;

/**
 * <p>Simple service providing the document generation functionality. The service doesn't take care about authorization nor the
 * operation validity.</p>
 *
 * @author Karel Cemus
 */
public interface PrintService {

    /** generates the confirmation about successful creation of the reservation */
    byte[] createConfirmation( Reservation reservation, String password );

    /** generates the confirmation about successful payment */
    byte[] createConfirmation( Payment payment );

    /** created e-ticket */
    byte[] createTicket( Reservation reservation );

    /** created the confirmation of reservation cancellation */
    byte[] createCancelConfirmation( Reservation reservation );
}
