package cz.cvut.fel.service;

import cz.cvut.fel.exception.InvalidPaymentException;
import cz.cvut.fel.exception.NoSuchReservationException;
import cz.cvut.fel.model.Payment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>Service makes money transactions between customers bank account and company's.</p>
 *
 * @author Karel Cemus
 */
public interface PaymentService extends Serializable {

    /** returns all payments attached to given reservation */
    List<Payment> findPayments( long reservationId, String password ) throws SecurityException, NoSuchReservationException;

    /** makes an payment from customer account to given reservation */
    Payment payVisa( long reservationId, String cardName, long creditCard, Date validUntil, int verificationCode ) throws InvalidPaymentException, NoSuchReservationException;

    /** if the reservation is cancelled customer can ask for sending back his money */
    Payment returnMoney( long reservationId, String password, long account, int bank ) throws SecurityException, InvalidPaymentException, NoSuchReservationException;

    /** prints confirmation that given payment has been accepted */
    byte[] printPaymentConfirmation( long payment );
}
