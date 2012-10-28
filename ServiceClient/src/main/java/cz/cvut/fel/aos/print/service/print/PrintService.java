package cz.cvut.fel.aos.print.service.print;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.3.1
 * Mon Oct 29 00:17:43 CET 2012
 * Generated source version: 2.3.1
 * 
 */
 
@WebService(targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", name = "PrintService")
@XmlSeeAlso({ObjectFactory.class})
public interface PrintService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "printPaymentConfirmation", targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.print.service.print.PrintPaymentConfirmation")
    @WebMethod
    @ResponseWrapper(localName = "printPaymentConfirmationResponse", targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.print.service.print.PrintPaymentConfirmationResponse")
    public javax.activation.DataHandler printPaymentConfirmation(
        @WebParam(name = "reservation", targetNamespace = "")
        long reservation,
        @WebParam(name = "cardNumber", targetNamespace = "")
        long cardNumber,
        @WebParam(name = "amount", targetNamespace = "")
        int amount
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "printTicket", targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.print.service.print.PrintTicket")
    @WebMethod
    @ResponseWrapper(localName = "printTicketResponse", targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.print.service.print.PrintTicketResponse")
    public javax.activation.DataHandler printTicket(
        @WebParam(name = "reservation", targetNamespace = "")
        long reservation,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "takeOff", targetNamespace = "")
        java.util.Date takeOff,
        @WebParam(name = "destination", targetNamespace = "")
        java.lang.String destination
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "printReservationConfirmation", targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.print.service.print.PrintReservationConfirmation")
    @WebMethod
    @ResponseWrapper(localName = "printReservationConfirmationResponse", targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.print.service.print.PrintReservationConfirmationResponse")
    public javax.activation.DataHandler printReservationConfirmation(
        @WebParam(name = "reservation", targetNamespace = "")
        long reservation,
        @WebParam(name = "password", targetNamespace = "")
        java.lang.String password,
        @WebParam(name = "takeOff", targetNamespace = "")
        java.util.Date takeOff,
        @WebParam(name = "destination", targetNamespace = "")
        java.lang.String destination,
        @WebParam(name = "cost", targetNamespace = "")
        java.lang.String cost
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "printCancelConfirmation", targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.print.service.print.PrintCancelConfirmation")
    @WebMethod
    @ResponseWrapper(localName = "printCancelConfirmationResponse", targetNamespace = "http://print.service.print.aos.fel.cvut.cz/", className = "cz.cvut.fel.aos.print.service.print.PrintCancelConfirmationResponse")
    public javax.activation.DataHandler printCancelConfirmation(
        @WebParam(name = "reservation", targetNamespace = "")
        long reservation,
        @WebParam(name = "takeOff", targetNamespace = "")
        java.util.Date takeOff,
        @WebParam(name = "destination", targetNamespace = "")
        java.lang.String destination
    );
}
