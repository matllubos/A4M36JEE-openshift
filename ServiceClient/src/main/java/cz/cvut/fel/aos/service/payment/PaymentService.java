package cz.cvut.fel.aos.service.payment;

import cz.cvut.fel.aos.service.reservation.Reservation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/** This class was generated by Apache CXF 2.3.1 Sat Nov 03 16:20:34 CET 2012 Generated source version: 2.3.1 */

@WebService( targetNamespace = "http://payment.service.aos.fel.cvut.cz/", name = "PaymentService" )
@XmlSeeAlso( { ObjectFactory.class } )
public interface PaymentService {

    @WebResult( name = "return", targetNamespace = "" )
    @RequestWrapper( localName = "transferFromReservation", targetNamespace = "http://payment.service.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.service.payment.TransferFromReservation" )
    @WebMethod
    @ResponseWrapper( localName = "transferFromReservationResponse", targetNamespace = "http://payment.service.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.service.payment.TransferFromReservationResponse" )
    public Reservation transferFromReservation(
            @WebParam( name = "reservationIdFrom", targetNamespace = "" )
            long reservationIdFrom,
            @WebParam( name = "passwordFrom", targetNamespace = "" )
            java.lang.String passwordFrom,
            @WebParam( name = "reservationIdTo", targetNamespace = "" )
            long reservationIdTo,
            @WebParam( name = "passwordTo", targetNamespace = "" )
            java.lang.String passwordTo
    ) throws NoSuchReservationException, SecurityException;

    @WebResult( name = "return", targetNamespace = "" )
    @RequestWrapper( localName = "payVisa", targetNamespace = "http://payment.service.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.service.payment.PayVisa" )
    @WebMethod
    @ResponseWrapper( localName = "payVisaResponse", targetNamespace = "http://payment.service.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.service.payment.PayVisaResponse" )
    public Reservation payVisa(
            @WebParam( name = "reservationId", targetNamespace = "" )
            long reservationId,
            @WebParam( name = "password", targetNamespace = "" )
            java.lang.String password,
            @WebParam( name = "cardName", targetNamespace = "" )
            java.lang.String cardName,
            @WebParam( name = "creditCard", targetNamespace = "" )
            long creditCard,
            @WebParam( name = "validUntil", targetNamespace = "" )
            java.util.Date validUntil,
            @WebParam( name = "verificationCode", targetNamespace = "" )
            int verificationCode
    ) throws NoSuchReservationException, SecurityException, InvalidPaymentException;
}
