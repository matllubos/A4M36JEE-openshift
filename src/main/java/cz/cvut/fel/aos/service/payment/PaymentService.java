package cz.cvut.fel.aos.service.payment;

import cz.cvut.fel.aos.model.Reservation;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;

/**
 * <p>Služba přijímající požadavky na platbu a vracející číslo transakce</p>
 *
 * @author Karel Cemus
 */
@WebService
public interface PaymentService {

    /** přijme platbu visa kartou */
    Reservation payVisa(
            @WebParam( name = "reservationId" ) long reservationId,
            @WebParam( name = "password" ) String password,
            @WebParam( name = "cardName" ) String cardName,
            @WebParam( name = "creditCard" ) long creditCard,
            @WebParam( name = "validUntil" ) Date validUntil,
            @WebParam( name = "verificationCode" ) int verificationCode
    ) throws SecurityException, InvalidPaymentException, NoSuchReservationException;

    /** převod peněz ze zrušené rezervace na platnou */
    Reservation transferFromReservation(
            @WebParam( name = "reservationIdFrom" ) long reservationIdFrom,
            @WebParam( name = "passwordFrom" ) String passwordFrom,
            @WebParam( name = "reservationIdTo" ) long reservationIdTo,
            @WebParam( name = "passwordTo" ) String passwordTo
    ) throws SecurityException, InvalidPaymentException, NoSuchReservationException;

}
