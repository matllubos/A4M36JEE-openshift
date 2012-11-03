package cz.cvut.fel.aos.service.payment;

import cz.cvut.fel.aos.service.destination.Destination;
import cz.cvut.fel.aos.service.flight.Flight;
import cz.cvut.fel.aos.service.reservation.Reservation;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the cz.cvut.fel.aos.service.payment package. <p>An
 * ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML content can consist of
 * schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model groups.  Factory methods for each of these
 * are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SecurityFault_QNAME = new QName( "http://payment.service.payment.aos.fel.cvut.cz/", "SecurityFault" );

    private final static QName _TransferFromReservation_QNAME = new QName( "http://payment.service.payment.aos.fel.cvut.cz/", "transferFromReservation" );

    private final static QName _NoSuchReservationFault_QNAME = new QName( "http://payment.service.payment.aos.fel.cvut.cz/", "NoSuchReservationFault" );

    private final static QName _InvalidPaymentFault_QNAME = new QName( "http://payment.service.payment.aos.fel.cvut.cz/", "InvalidPaymentFault" );

    private final static QName _PayVisaResponse_QNAME = new QName( "http://payment.service.payment.aos.fel.cvut.cz/", "payVisaResponse" );

    private final static QName _TransferFromReservationResponse_QNAME = new QName( "http://payment.service.payment.aos.fel.cvut.cz/", "transferFromReservationResponse" );

    private final static QName _PayVisa_QNAME = new QName( "http://payment.service.payment.aos.fel.cvut.cz/", "payVisa" );

    /** Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cvut.fel.aos.service.payment */
    public ObjectFactory() {
    }

    /** Create an instance of {@link TransferFromReservationResponse } */
    public TransferFromReservationResponse createTransferFromReservationResponse() {
        return new TransferFromReservationResponse();
    }

    /** Create an instance of {@link PayVisa } */
    public PayVisa createPayVisa() {
        return new PayVisa();
    }

    /** Create an instance of {@link SecurityFault } */
    public SecurityFault createSecurityFault() {
        return new SecurityFault();
    }

    /** Create an instance of {@link InvalidPaymentFault } */
    public InvalidPaymentFault createInvalidPaymentFault() {
        return new InvalidPaymentFault();
    }

    /** Create an instance of {@link NoSuchReservationFault } */
    public NoSuchReservationFault createNoSuchReservationFault() {
        return new NoSuchReservationFault();
    }

    /** Create an instance of {@link TransferFromReservation } */
    public TransferFromReservation createTransferFromReservation() {
        return new TransferFromReservation();
    }

    /** Create an instance of {@link PayVisaResponse } */
    public PayVisaResponse createPayVisaResponse() {
        return new PayVisaResponse();
    }

    /** Create an instance of {@link Flight } */
    public Flight createFlight() {
        return new Flight();
    }

    /** Create an instance of {@link Reservation } */
    public Reservation createReservation() {
        return new Reservation();
    }

    /** Create an instance of {@link Destination } */
    public Destination createDestination() {
        return new Destination();
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link SecurityFault }{@code >}} */
    @XmlElementDecl( namespace = "http://payment.service.payment.aos.fel.cvut.cz/", name = "SecurityFault" )
    public JAXBElement<SecurityFault> createSecurityFault( SecurityFault value ) {
        return new JAXBElement<SecurityFault>( _SecurityFault_QNAME, SecurityFault.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link TransferFromReservation }{@code >}} */
    @XmlElementDecl( namespace = "http://payment.service.payment.aos.fel.cvut.cz/", name = "transferFromReservation" )
    public JAXBElement<TransferFromReservation> createTransferFromReservation( TransferFromReservation value ) {
        return new JAXBElement<TransferFromReservation>( _TransferFromReservation_QNAME, TransferFromReservation.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link NoSuchReservationFault }{@code >}} */
    @XmlElementDecl( namespace = "http://payment.service.payment.aos.fel.cvut.cz/", name = "NoSuchReservationFault" )
    public JAXBElement<NoSuchReservationFault> createNoSuchReservationFault( NoSuchReservationFault value ) {
        return new JAXBElement<NoSuchReservationFault>( _NoSuchReservationFault_QNAME, NoSuchReservationFault.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link InvalidPaymentFault }{@code >}} */
    @XmlElementDecl( namespace = "http://payment.service.payment.aos.fel.cvut.cz/", name = "InvalidPaymentFault" )
    public JAXBElement<InvalidPaymentFault> createInvalidPaymentFault( InvalidPaymentFault value ) {
        return new JAXBElement<InvalidPaymentFault>( _InvalidPaymentFault_QNAME, InvalidPaymentFault.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link PayVisaResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://payment.service.payment.aos.fel.cvut.cz/", name = "payVisaResponse" )
    public JAXBElement<PayVisaResponse> createPayVisaResponse( PayVisaResponse value ) {
        return new JAXBElement<PayVisaResponse>( _PayVisaResponse_QNAME, PayVisaResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link TransferFromReservationResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://payment.service.payment.aos.fel.cvut.cz/", name = "transferFromReservationResponse" )
    public JAXBElement<TransferFromReservationResponse> createTransferFromReservationResponse( TransferFromReservationResponse value ) {
        return new JAXBElement<TransferFromReservationResponse>( _TransferFromReservationResponse_QNAME, TransferFromReservationResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link PayVisa }{@code >}} */
    @XmlElementDecl( namespace = "http://payment.service.payment.aos.fel.cvut.cz/", name = "payVisa" )
    public JAXBElement<PayVisa> createPayVisa( PayVisa value ) {
        return new JAXBElement<PayVisa>( _PayVisa_QNAME, PayVisa.class, null, value );
    }

}
