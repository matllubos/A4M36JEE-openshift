package cz.cvut.fel.beans;

import cz.cvut.fel.model.Reservation;
import cz.cvut.fel.service.PaymentService;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

/** @author Karel Cemus */
@Data
@ViewScoped
@Named( "paymentBean" )
@EqualsAndHashCode( callSuper = false )
public class PaymentBean extends BeanBase implements Serializable {

    @Inject
    private ReservationBean reservationBean;

    @Inject
    private PaymentService service;

    private static final TimeZone TIMEZONE = TimeZone.getDefault();

    private long creditCardNumber;

    private String creditCardName;

    private Reservation reservation;

    private Date validUntil;

    private int verificationCode;

    public static TimeZone getTimezone() {
        return TIMEZONE;
    }

    @AssertTrue( message = "Verification code is wrong" )
    public boolean isVerifyCard() {
        // verification code == last 3 digits of card number
        return creditCardNumber % 1000 == verificationCode;
    }

    @Future( message = "Card has expired" )
    @NotNull( message = "Field is required" )
    public Date getValidUntil() {
        return validUntil;
    }

    @NotNull( message = "You are not logged in, no reservation is attached" )
    public Reservation getReservation() {
        return reservation;
    }

    @Min( value = 1000000000000000L, message = "Card number must be in format XXXX-XXXX-XXXX-XXXX" )
    @Max( value = 9999999999999999L, message = "Card number must be in format XXXX-XXXX-XXXX-XXXX" )
    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    @NotNull
    public String getCreditCardName() {
        return creditCardName;
    }

    public String pay() {

        try {
            // try to make transaction
            service.payVisa( reservation.getId(), creditCardName, creditCardNumber, validUntil, verificationCode );

            // log success
            addInformation( "Payment accepted." );

            // reload session scoped data
            reservationBean.reload();

            // redirect back to reservation overview
            return "reservation";

        } catch ( Throwable ex ) {
            addError( processException( ex ) );
            return null;
        }
    }

    @PostConstruct
    public void init() {

        // load reservation
        if ( reservationBean != null && reservationBean.getReservation() != null ) {
            reservation = reservationBean.getReservation();
        }
    }

}
