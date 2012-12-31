package cz.cvut.fel.service;

import cz.cvut.fel.exception.InvalidPaymentException;
import cz.cvut.fel.exception.NoSuchReservationException;
import cz.cvut.fel.model.Payment;

import java.util.Date;

/**
 * <p>Service makes money transactions between customers bank account and company's.</p>
 *
 * @author Karel Cemus
 */
public interface PaymentService {

    /** makes an payment from customer account to given reservation */
    Payment payVisa( long reservationId, String cardName, long creditCard, Date validUntil, int verificationCode ) throws InvalidPaymentException, NoSuchReservationException;

    /** if the reservation is cancelled customer can ask for sending back his money */
    Payment returnMoney( long reservationId, String password, long creditCard ) throws SecurityException, InvalidPaymentException, NoSuchReservationException;

    /** prints confirmation that given payment has been accepted */
    byte[] printPaymentConfirmation( long payment );
}
