package cz.cvut.fel.aos.service.flight;

import cz.cvut.fel.aos.service.destination.Destination;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the cz.cvut.fel.aos.service.flight package. <p>An
 * ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML content can consist of
 * schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model groups.  Factory methods for each of these
 * are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindAllResponse_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findAllResponse" );

    private final static QName _FindFlightsFrom_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findFlightsFrom" );

    private final static QName _Find_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "find" );

    private final static QName _FindFlights_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findFlights" );

    private final static QName _FindFlightsTo_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findFlightsTo" );

    private final static QName _FindResponse_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findResponse" );

    private final static QName _FindAll_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findAll" );

    private final static QName _FindFlightsFromResponse_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findFlightsFromResponse" );

    private final static QName _FindFlightsResponse_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findFlightsResponse" );

    private final static QName _FindFlightsToResponse_QNAME = new QName( "http://flight.service.booking.aos.fel.cvut.cz/", "findFlightsToResponse" );

    /** Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cvut.fel.aos.service.flight */
    public ObjectFactory() {
    }

    /** Create an instance of {@link FindFlightsToResponse } */
    public FindFlightsToResponse createFindFlightsToResponse() {
        return new FindFlightsToResponse();
    }

    /** Create an instance of {@link FindFlightsResponse } */
    public FindFlightsResponse createFindFlightsResponse() {
        return new FindFlightsResponse();
    }

    /** Create an instance of {@link FindFlightsFromResponse } */
    public FindFlightsFromResponse createFindFlightsFromResponse() {
        return new FindFlightsFromResponse();
    }

    /** Create an instance of {@link FindAll } */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /** Create an instance of {@link FindResponse } */
    public FindResponse createFindResponse() {
        return new FindResponse();
    }

    /** Create an instance of {@link FindFlightsTo } */
    public FindFlightsTo createFindFlightsTo() {
        return new FindFlightsTo();
    }

    /** Create an instance of {@link FindFlights } */
    public FindFlights createFindFlights() {
        return new FindFlights();
    }

    /** Create an instance of {@link Find } */
    public Find createFind() {
        return new Find();
    }

    /** Create an instance of {@link FindFlightsFrom } */
    public FindFlightsFrom createFindFlightsFrom() {
        return new FindFlightsFrom();
    }

    /** Create an instance of {@link FindAllResponse } */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /** Create an instance of {@link Flight } */
    public Flight createFlight() {
        return new Flight();
    }

    /** Create an instance of {@link Destination } */
    public Destination createDestination() {
        return new Destination();
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findAllResponse" )
    public JAXBElement<FindAllResponse> createFindAllResponse( FindAllResponse value ) {
        return new JAXBElement<FindAllResponse>( _FindAllResponse_QNAME, FindAllResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsFrom }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findFlightsFrom" )
    public JAXBElement<FindFlightsFrom> createFindFlightsFrom( FindFlightsFrom value ) {
        return new JAXBElement<FindFlightsFrom>( _FindFlightsFrom_QNAME, FindFlightsFrom.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link Find }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "find" )
    public JAXBElement<Find> createFind( Find value ) {
        return new JAXBElement<Find>( _Find_QNAME, Find.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlights }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findFlights" )
    public JAXBElement<FindFlights> createFindFlights( FindFlights value ) {
        return new JAXBElement<FindFlights>( _FindFlights_QNAME, FindFlights.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsTo }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findFlightsTo" )
    public JAXBElement<FindFlightsTo> createFindFlightsTo( FindFlightsTo value ) {
        return new JAXBElement<FindFlightsTo>( _FindFlightsTo_QNAME, FindFlightsTo.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findResponse" )
    public JAXBElement<FindResponse> createFindResponse( FindResponse value ) {
        return new JAXBElement<FindResponse>( _FindResponse_QNAME, FindResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findAll" )
    public JAXBElement<FindAll> createFindAll( FindAll value ) {
        return new JAXBElement<FindAll>( _FindAll_QNAME, FindAll.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsFromResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findFlightsFromResponse" )
    public JAXBElement<FindFlightsFromResponse> createFindFlightsFromResponse( FindFlightsFromResponse value ) {
        return new JAXBElement<FindFlightsFromResponse>( _FindFlightsFromResponse_QNAME, FindFlightsFromResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findFlightsResponse" )
    public JAXBElement<FindFlightsResponse> createFindFlightsResponse( FindFlightsResponse value ) {
        return new JAXBElement<FindFlightsResponse>( _FindFlightsResponse_QNAME, FindFlightsResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindFlightsToResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://flight.service.booking.aos.fel.cvut.cz/", name = "findFlightsToResponse" )
    public JAXBElement<FindFlightsToResponse> createFindFlightsToResponse( FindFlightsToResponse value ) {
        return new JAXBElement<FindFlightsToResponse>( _FindFlightsToResponse_QNAME, FindFlightsToResponse.class, null, value );
    }

}
