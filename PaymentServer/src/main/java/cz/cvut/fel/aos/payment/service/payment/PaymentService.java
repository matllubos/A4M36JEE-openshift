package cz.cvut.fel.aos.payment.service.payment;

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
    long payVisa( long reservationId, long creditCard, String name, Date validUntil, int code );

//    long transferFromOtherReservation

    /** přijme platbu pro danou rezervaci. Heslo v podobě SHA-1 */
//    Reservation pay(
//            @WebParam( name = "reservation" ) long reservation,
//            @WebParam( name = "password" ) String password,
//            @WebParam( name = "amount" ) int amount
//    ) throws SecurityException;
//
//
//    /** vybere peníze vložené na zrušenou rezervaci. Lze použít pro platbu jiné rezervace */
//    int withdrawCredit(
//            @WebParam( name = "reservation" ) long reservation,
//            @WebParam( name = "password" ) String password,
//            @WebParam( name = "amount" ) int amount
//    ) throws SecurityException;

}
