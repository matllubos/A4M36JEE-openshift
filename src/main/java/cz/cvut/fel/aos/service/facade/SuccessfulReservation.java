package cz.cvut.fel.aos.service.facade;

import cz.cvut.fel.aos.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.activation.DataHandler;

/**
 * <p>Přepravka pro úspěšné provedení rezervace </p>
 *
 * @author Karel Cemus
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulReservation {

    private Reservation reservation;

    private DataHandler confirmation;
}
