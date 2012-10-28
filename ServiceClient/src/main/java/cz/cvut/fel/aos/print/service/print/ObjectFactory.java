
package cz.cvut.fel.aos.print.service.print;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.cvut.fel.aos.print.service.print package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PrintTicket_QNAME = new QName("http://print.service.print.aos.fel.cvut.cz/", "printTicket");
    private final static QName _PrintPaymentConfirmation_QNAME = new QName("http://print.service.print.aos.fel.cvut.cz/", "printPaymentConfirmation");
    private final static QName _PrintPaymentConfirmationResponse_QNAME = new QName("http://print.service.print.aos.fel.cvut.cz/", "printPaymentConfirmationResponse");
    private final static QName _PrintTicketResponse_QNAME = new QName("http://print.service.print.aos.fel.cvut.cz/", "printTicketResponse");
    private final static QName _PrintCancelConfirmationResponse_QNAME = new QName("http://print.service.print.aos.fel.cvut.cz/", "printCancelConfirmationResponse");
    private final static QName _PrintReservationConfirmation_QNAME = new QName("http://print.service.print.aos.fel.cvut.cz/", "printReservationConfirmation");
    private final static QName _PrintCancelConfirmation_QNAME = new QName("http://print.service.print.aos.fel.cvut.cz/", "printCancelConfirmation");
    private final static QName _PrintReservationConfirmationResponse_QNAME = new QName("http://print.service.print.aos.fel.cvut.cz/", "printReservationConfirmationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cvut.fel.aos.print.service.print
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PrintReservationConfirmation }
     * 
     */
    public PrintReservationConfirmation createPrintReservationConfirmation() {
        return new PrintReservationConfirmation();
    }

    /**
     * Create an instance of {@link PrintReservationConfirmationResponse }
     * 
     */
    public PrintReservationConfirmationResponse createPrintReservationConfirmationResponse() {
        return new PrintReservationConfirmationResponse();
    }

    /**
     * Create an instance of {@link PrintCancelConfirmation }
     * 
     */
    public PrintCancelConfirmation createPrintCancelConfirmation() {
        return new PrintCancelConfirmation();
    }

    /**
     * Create an instance of {@link PrintTicketResponse }
     * 
     */
    public PrintTicketResponse createPrintTicketResponse() {
        return new PrintTicketResponse();
    }

    /**
     * Create an instance of {@link PrintPaymentConfirmationResponse }
     * 
     */
    public PrintPaymentConfirmationResponse createPrintPaymentConfirmationResponse() {
        return new PrintPaymentConfirmationResponse();
    }

    /**
     * Create an instance of {@link PrintCancelConfirmationResponse }
     * 
     */
    public PrintCancelConfirmationResponse createPrintCancelConfirmationResponse() {
        return new PrintCancelConfirmationResponse();
    }

    /**
     * Create an instance of {@link PrintTicket }
     * 
     */
    public PrintTicket createPrintTicket() {
        return new PrintTicket();
    }

    /**
     * Create an instance of {@link PrintPaymentConfirmation }
     * 
     */
    public PrintPaymentConfirmation createPrintPaymentConfirmation() {
        return new PrintPaymentConfirmation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintTicket }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://print.service.print.aos.fel.cvut.cz/", name = "printTicket")
    public JAXBElement<PrintTicket> createPrintTicket(PrintTicket value) {
        return new JAXBElement<PrintTicket>(_PrintTicket_QNAME, PrintTicket.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintPaymentConfirmation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://print.service.print.aos.fel.cvut.cz/", name = "printPaymentConfirmation")
    public JAXBElement<PrintPaymentConfirmation> createPrintPaymentConfirmation(PrintPaymentConfirmation value) {
        return new JAXBElement<PrintPaymentConfirmation>(_PrintPaymentConfirmation_QNAME, PrintPaymentConfirmation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintPaymentConfirmationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://print.service.print.aos.fel.cvut.cz/", name = "printPaymentConfirmationResponse")
    public JAXBElement<PrintPaymentConfirmationResponse> createPrintPaymentConfirmationResponse(PrintPaymentConfirmationResponse value) {
        return new JAXBElement<PrintPaymentConfirmationResponse>(_PrintPaymentConfirmationResponse_QNAME, PrintPaymentConfirmationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintTicketResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://print.service.print.aos.fel.cvut.cz/", name = "printTicketResponse")
    public JAXBElement<PrintTicketResponse> createPrintTicketResponse(PrintTicketResponse value) {
        return new JAXBElement<PrintTicketResponse>(_PrintTicketResponse_QNAME, PrintTicketResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintCancelConfirmationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://print.service.print.aos.fel.cvut.cz/", name = "printCancelConfirmationResponse")
    public JAXBElement<PrintCancelConfirmationResponse> createPrintCancelConfirmationResponse(PrintCancelConfirmationResponse value) {
        return new JAXBElement<PrintCancelConfirmationResponse>(_PrintCancelConfirmationResponse_QNAME, PrintCancelConfirmationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintReservationConfirmation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://print.service.print.aos.fel.cvut.cz/", name = "printReservationConfirmation")
    public JAXBElement<PrintReservationConfirmation> createPrintReservationConfirmation(PrintReservationConfirmation value) {
        return new JAXBElement<PrintReservationConfirmation>(_PrintReservationConfirmation_QNAME, PrintReservationConfirmation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintCancelConfirmation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://print.service.print.aos.fel.cvut.cz/", name = "printCancelConfirmation")
    public JAXBElement<PrintCancelConfirmation> createPrintCancelConfirmation(PrintCancelConfirmation value) {
        return new JAXBElement<PrintCancelConfirmation>(_PrintCancelConfirmation_QNAME, PrintCancelConfirmation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrintReservationConfirmationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://print.service.print.aos.fel.cvut.cz/", name = "printReservationConfirmationResponse")
    public JAXBElement<PrintReservationConfirmationResponse> createPrintReservationConfirmationResponse(PrintReservationConfirmationResponse value) {
        return new JAXBElement<PrintReservationConfirmationResponse>(_PrintReservationConfirmationResponse_QNAME, PrintReservationConfirmationResponse.class, null, value);
    }

}
