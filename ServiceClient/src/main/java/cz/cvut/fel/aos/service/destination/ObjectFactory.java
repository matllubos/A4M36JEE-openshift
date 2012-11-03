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

    private final static QName _FindAllResponse_QNAME = new QName( "http://destination.service.aos.fel.cvut.cz/", "findAllResponse" );

    private final static QName _FindAll_QNAME = new QName( "http://destination.service.aos.fel.cvut.cz/", "findAll" );

    /** Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cvut.fel.aos.service.destination */
    public ObjectFactory() {
    }

    /** Create an instance of {@link FindAll } */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /** Create an instance of {@link FindAllResponse } */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /** Create an instance of {@link Destination } */
    public Destination createDestination() {
        return new Destination();
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}} */
    @XmlElementDecl( namespace = "http://destination.service.aos.fel.cvut.cz/", name = "findAllResponse" )
    public JAXBElement<FindAllResponse> createFindAllResponse( FindAllResponse value ) {
        return new JAXBElement<FindAllResponse>( _FindAllResponse_QNAME, FindAllResponse.class, null, value );
    }

    /** Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}} */
    @XmlElementDecl( namespace = "http://destination.service.aos.fel.cvut.cz/", name = "findAll" )
    public JAXBElement<FindAll> createFindAll( FindAll value ) {
        return new JAXBElement<FindAll>( _FindAll_QNAME, FindAll.class, null, value );
    }

}
