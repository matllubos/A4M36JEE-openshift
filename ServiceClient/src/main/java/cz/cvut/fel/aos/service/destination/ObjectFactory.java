package cz.cvut.fel.aos.service.destination;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the cz.cvut.fel.aos.service.destination package. <p>An
 * ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java representation of XML content can consist of
 * schema derived interfaces and classes representing the binding of schema type definitions, element declarations and model groups.  Factory methods for each of these
 * are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindAllDestinationsResponse_QNAME = new QName( "http://destination.service.aos.fel.cvut.cz/", "findAllDestinationsResponse" );

    private final static QName _FindAllDestinations_QNAME = new QName( "http://destination.service.aos.fel.cvut.cz/", "findAllDestinations" );

    /** Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cvut.fel.aos.service.destination */
    public ObjectFactory() {
    }

    /** Create an instance of {@link FindAllDestinations } */
    public FindAllDestinations createFindAllDestinations() {
        return new FindAllDestinations();
    }

    /** Create an instance of {@link FindAllDestinationsResponse } */
    public FindAllDestinationsResponse createFindAllDestinationsResponse() {
        return new FindAllDestinationsResponse();
    }

    /** Create an instance of {@link Destination } */
    public Destination createDestination() {
        return new Destination();
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindAllDestinationsResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://destination.service.aos.fel.cvut.cz/", name = "findAllDestinationsResponse" )
    public JAXBElement<FindAllDestinationsResponse> createFindAllDestinationsResponse( FindAllDestinationsResponse value ) {
        return new JAXBElement<FindAllDestinationsResponse>( _FindAllDestinationsResponse_QNAME, FindAllDestinationsResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindAllDestinations }{@code >}} */
    @XmlElementDecl( namespace = "http://destination.service.aos.fel.cvut.cz/", name = "findAllDestinations" )
    public JAXBElement<FindAllDestinations> createFindAllDestinations( FindAllDestinations value ) {
        return new JAXBElement<FindAllDestinations>( _FindAllDestinations_QNAME, FindAllDestinations.class, null, value );
    }

}
