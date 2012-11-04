package cz.cvut.fel.aos.service.facade;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the cz.cvut.fel.aos.service.facade package. <p>An
 * ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML content can consist of
 * schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model groups.  Factory methods for each of these
 * are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindFlightsToResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findFlightsToResponse" );

    private final static QName _FindAllDestinationsResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findAllDestinationsResponse" );

    private final static QName _FindReservationResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findReservationResponse" );

    private final static QName _FindFlightsFromResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findFlightsFromResponse" );

    private final static QName _ReservationNotPaidFault_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "ReservationNotPaidFault" );

    private final static QName _PrintTicket_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "printTicket" );

    private final static QName _PayFromCanceledReservation_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "payFromCanceledReservation" );

    private final static QName _FindFlightsTo_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findFlightsTo" );

    private final static QName _CreateReservationResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "createReservationResponse" );

    private final static QName _FindAllDestinations_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findAllDestinations" );

    private final static QName _FullFlightFault_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "FullFlightFault" );

    private final static QName _NoSuchReservationFault_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "NoSuchReservationFault" );

    private final static QName _CancelReservation_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "cancelReservation" );

    private final static QName _FindFlightsResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findFlightsResponse" );

    private final static QName _PrintTicketResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "printTicketResponse" );

    private final static QName _PayVisa_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "payVisa" );

    private final static QName _SecurityFault_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "SecurityFault" );

    private final static QName _FindFlights_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findFlights" );

    private final static QName _FindReservation_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findReservation" );

    private final static QName _CancelReservationResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "cancelReservationResponse" );

    private final static QName _PayFromCanceledReservationResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "payFromCanceledReservationResponse" );

    private final static QName _CreateReservation_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "createReservation" );

    private final static QName _InvalidPaymentFault_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "InvalidPaymentFault" );

    private final static QName _PayVisaResponse_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "payVisaResponse" );

    private final static QName _FindFlightsFrom_QNAME = new QName( "http://facade.service.aos.fel.cvut.cz/", "findFlightsFrom" );

    /** Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cvut.fel.aos.service.facade */
    public ObjectFactory() {
    }

    /** Create an instance of {@link FindAllDestinations } */
    public FindAllDestinations createFindAllDestinations() {
        return new FindAllDestinations();
    }

    /** Create an instance of {@link CreateReservationResponse } */
    public CreateReservationResponse createCreateReservationResponse() {
        return new CreateReservationResponse();
    }

    /** Create an instance of {@link FullFlightFault } */
    public FullFlightFault createFullFlightFault() {
        return new FullFlightFault();
    }

    /** Create an instance of {@link NoSuchReservationFault } */
    public NoSuchReservationFault createNoSuchReservationFault() {
        return new NoSuchReservationFault();
    }

    /** Create an instance of {@link CancelReservation } */
    public CancelReservation createCancelReservation() {
        return new CancelReservation();
    }

    /** Create an instance of {@link FindFlightsToResponse } */
    public FindFlightsToResponse createFindFlightsToResponse() {
        return new FindFlightsToResponse();
    }

    /** Create an instance of {@link FindAllDestinationsResponse } */
    public FindAllDestinationsResponse createFindAllDestinationsResponse() {
        return new FindAllDestinationsResponse();
    }

    /** Create an instance of {@link FindFlightsFromResponse } */
    public FindFlightsFromResponse createFindFlightsFromResponse() {
        return new FindFlightsFromResponse();
    }

    /** Create an instance of {@link FindReservationResponse } */
    public FindReservationResponse createFindReservationResponse() {
        return new FindReservationResponse();
    }

    /** Create an instance of {@link ReservationNotPaidFault } */
    public ReservationNotPaidFault createReservationNotPaidFault() {
        return new ReservationNotPaidFault();
    }

    /** Create an instance of {@link PrintTicket } */
    public PrintTicket createPrintTicket() {
        return new PrintTicket();
    }

    /** Create an instance of {@link FindFlightsTo } */
    public FindFlightsTo createFindFlightsTo() {
        return new FindFlightsTo();
    }

    /** Create an instance of {@link PayFromCanceledReservation } */
    public PayFromCanceledReservation createPayFromCanceledReservation() {
        return new PayFromCanceledReservation();
    }

    /** Create an instance of {@link FindReservation } */
    public FindReservation createFindReservation() {
        return new FindReservation();
    }

    /** Create an instance of {@link PayFromCanceledReservationResponse } */
    public PayFromCanceledReservationResponse createPayFromCanceledReservationResponse() {
        return new PayFromCanceledReservationResponse();
    }

    /** Create an instance of {@link CancelReservationResponse } */
    public CancelReservationResponse createCancelReservationResponse() {
        return new CancelReservationResponse();
    }

    /** Create an instance of {@link InvalidPaymentFault } */
    public InvalidPaymentFault createInvalidPaymentFault() {
        return new InvalidPaymentFault();
    }

    /** Create an instance of {@link CreateReservation } */
    public CreateReservation createCreateReservation() {
        return new CreateReservation();
    }

    /** Create an instance of {@link PayVisaResponse } */
    public PayVisaResponse createPayVisaResponse() {
        return new PayVisaResponse();
    }

    /** Create an instance of {@link FindFlightsFrom } */
    public FindFlightsFrom createFindFlightsFrom() {
        return new FindFlightsFrom();
    }

    /** Create an instance of {@link FindFlightsResponse } */
    public FindFlightsResponse createFindFlightsResponse() {
        return new FindFlightsResponse();
    }

    /** Create an instance of {@link PrintTicketResponse } */
    public PrintTicketResponse createPrintTicketResponse() {
        return new PrintTicketResponse();
    }

    /** Create an instance of {@link PayVisa } */
    public PayVisa createPayVisa() {
        return new PayVisa();
    }

    /** Create an instance of {@link SecurityFault } */
    public SecurityFault createSecurityFault() {
        return new SecurityFault();
    }

    /** Create an instance of {@link FindFlights } */
    public FindFlights createFindFlights() {
        return new FindFlights();
    }

    /** Create an instance of {@link SuccessfulReservation } */
    public SuccessfulReservation createSuccessfulReservation() {
        return new SuccessfulReservation();
    }

    /** Create an instance of {@link Destination } */
    public Destination createDestination() {
        return new Destination();
    }

    /** Create an instance of {@link Flight } */
    public Flight createFlight() {
        return new Flight();
    }

    /** Create an instance of {@link Reservation } */
    public Reservation createReservation() {
        return new Reservation();
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsToResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findFlightsToResponse" )
    public JAXBElement<FindFlightsToResponse> createFindFlightsToResponse( FindFlightsToResponse value ) {
        return new JAXBElement<FindFlightsToResponse>( _FindFlightsToResponse_QNAME, FindFlightsToResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindAllDestinationsResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findAllDestinationsResponse" )
    public JAXBElement<FindAllDestinationsResponse> createFindAllDestinationsResponse( FindAllDestinationsResponse value ) {
        return new JAXBElement<FindAllDestinationsResponse>( _FindAllDestinationsResponse_QNAME, FindAllDestinationsResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindReservationResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findReservationResponse" )
    public JAXBElement<FindReservationResponse> createFindReservationResponse( FindReservationResponse value ) {
        return new JAXBElement<FindReservationResponse>( _FindReservationResponse_QNAME, FindReservationResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsFromResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findFlightsFromResponse" )
    public JAXBElement<FindFlightsFromResponse> createFindFlightsFromResponse( FindFlightsFromResponse value ) {
        return new JAXBElement<FindFlightsFromResponse>( _FindFlightsFromResponse_QNAME, FindFlightsFromResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link ReservationNotPaidFault }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "ReservationNotPaidFault" )
    public JAXBElement<ReservationNotPaidFault> createReservationNotPaidFault( ReservationNotPaidFault value ) {
        return new JAXBElement<ReservationNotPaidFault>( _ReservationNotPaidFault_QNAME, ReservationNotPaidFault.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link PrintTicket }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "printTicket" )
    public JAXBElement<PrintTicket> createPrintTicket( PrintTicket value ) {
        return new JAXBElement<PrintTicket>( _PrintTicket_QNAME, PrintTicket.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link PayFromCanceledReservation }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "payFromCanceledReservation" )
    public JAXBElement<PayFromCanceledReservation> createPayFromCanceledReservation( PayFromCanceledReservation value ) {
        return new JAXBElement<PayFromCanceledReservation>( _PayFromCanceledReservation_QNAME, PayFromCanceledReservation.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsTo }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findFlightsTo" )
    public JAXBElement<FindFlightsTo> createFindFlightsTo( FindFlightsTo value ) {
        return new JAXBElement<FindFlightsTo>( _FindFlightsTo_QNAME, FindFlightsTo.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link CreateReservationResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "createReservationResponse" )
    public JAXBElement<CreateReservationResponse> createCreateReservationResponse( CreateReservationResponse value ) {
        return new JAXBElement<CreateReservationResponse>( _CreateReservationResponse_QNAME, CreateReservationResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindAllDestinations }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findAllDestinations" )
    public JAXBElement<FindAllDestinations> createFindAllDestinations( FindAllDestinations value ) {
        return new JAXBElement<FindAllDestinations>( _FindAllDestinations_QNAME, FindAllDestinations.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FullFlightFault }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "FullFlightFault" )
    public JAXBElement<FullFlightFault> createFullFlightFault( FullFlightFault value ) {
        return new JAXBElement<FullFlightFault>( _FullFlightFault_QNAME, FullFlightFault.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link NoSuchReservationFault }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "NoSuchReservationFault" )
    public JAXBElement<NoSuchReservationFault> createNoSuchReservationFault( NoSuchReservationFault value ) {
        return new JAXBElement<NoSuchReservationFault>( _NoSuchReservationFault_QNAME, NoSuchReservationFault.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link CancelReservation }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "cancelReservation" )
    public JAXBElement<CancelReservation> createCancelReservation( CancelReservation value ) {
        return new JAXBElement<CancelReservation>( _CancelReservation_QNAME, CancelReservation.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findFlightsResponse" )
    public JAXBElement<FindFlightsResponse> createFindFlightsResponse( FindFlightsResponse value ) {
        return new JAXBElement<FindFlightsResponse>( _FindFlightsResponse_QNAME, FindFlightsResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link PrintTicketResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "printTicketResponse" )
    public JAXBElement<PrintTicketResponse> createPrintTicketResponse( PrintTicketResponse value ) {
        return new JAXBElement<PrintTicketResponse>( _PrintTicketResponse_QNAME, PrintTicketResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link PayVisa }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "payVisa" )
    public JAXBElement<PayVisa> createPayVisa( PayVisa value ) {
        return new JAXBElement<PayVisa>( _PayVisa_QNAME, PayVisa.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link SecurityFault }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "SecurityFault" )
    public JAXBElement<SecurityFault> createSecurityFault( SecurityFault value ) {
        return new JAXBElement<SecurityFault>( _SecurityFault_QNAME, SecurityFault.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlights }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findFlights" )
    public JAXBElement<FindFlights> createFindFlights( FindFlights value ) {
        return new JAXBElement<FindFlights>( _FindFlights_QNAME, FindFlights.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindReservation }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findReservation" )
    public JAXBElement<FindReservation> createFindReservation( FindReservation value ) {
        return new JAXBElement<FindReservation>( _FindReservation_QNAME, FindReservation.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link CancelReservationResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "cancelReservationResponse" )
    public JAXBElement<CancelReservationResponse> createCancelReservationResponse( CancelReservationResponse value ) {
        return new JAXBElement<CancelReservationResponse>( _CancelReservationResponse_QNAME, CancelReservationResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link PayFromCanceledReservationResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "payFromCanceledReservationResponse" )
    public JAXBElement<PayFromCanceledReservationResponse> createPayFromCanceledReservationResponse( PayFromCanceledReservationResponse value ) {
        return new JAXBElement<PayFromCanceledReservationResponse>( _PayFromCanceledReservationResponse_QNAME, PayFromCanceledReservationResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link CreateReservation }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "createReservation" )
    public JAXBElement<CreateReservation> createCreateReservation( CreateReservation value ) {
        return new JAXBElement<CreateReservation>( _CreateReservation_QNAME, CreateReservation.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link InvalidPaymentFault }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "InvalidPaymentFault" )
    public JAXBElement<InvalidPaymentFault> createInvalidPaymentFault( InvalidPaymentFault value ) {
        return new JAXBElement<InvalidPaymentFault>( _InvalidPaymentFault_QNAME, InvalidPaymentFault.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link PayVisaResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "payVisaResponse" )
    public JAXBElement<PayVisaResponse> createPayVisaResponse( PayVisaResponse value ) {
        return new JAXBElement<PayVisaResponse>( _PayVisaResponse_QNAME, PayVisaResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsFrom }{@code >}} */
    @XmlElementDecl( namespace = "http://facade.service.aos.fel.cvut.cz/", name = "findFlightsFrom" )
    public JAXBElement<FindFlightsFrom> createFindFlightsFrom( FindFlightsFrom value ) {
        return new JAXBElement<FindFlightsFrom>( _FindFlightsFrom_QNAME, FindFlightsFrom.class, null, value );
    }

}
