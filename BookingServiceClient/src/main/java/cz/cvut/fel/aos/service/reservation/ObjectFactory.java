
package cz.cvut.fel.aos.service.reservation;

import cz.cvut.fel.aos.service.destination.Destination;
import cz.cvut.fel.aos.service.flight.Flight;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cz.cvut.fel.aos.service.reservation package. 
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

    private final static QName _CancelResponse_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "cancelResponse");
    private final static QName _FullFlightFault_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "FullFlightFault");
    private final static QName _PayResponse_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "payResponse");
    private final static QName _WithdrawCreditResponse_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "withdrawCreditResponse");
    private final static QName _CreateResponse_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "createResponse");
    private final static QName _FindResponse_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "findResponse");
    private final static QName _WithdrawCredit_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "withdrawCredit");
    private final static QName _SecurityFault_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "SecurityFault");
    private final static QName _Pay_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "pay");
    private final static QName _Cancel_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "cancel");
    private final static QName _Create_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "create");
    private final static QName _Find_QNAME = new QName("http://reservation.service.bookingserver.aos.fel.cvut.cz/", "find");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cz.cvut.fel.aos.service.reservation
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CancelResponse }
     * 
     */
    public CancelResponse createCancelResponse() {
        return new CancelResponse();
    }

    /**
     * Create an instance of {@link FullFlightFault }
     * 
     */
    public FullFlightFault createFullFlightFault() {
        return new FullFlightFault();
    }

    /**
     * Create an instance of {@link PayResponse }
     * 
     */
    public PayResponse createPayResponse() {
        return new PayResponse();
    }

    /**
     * Create an instance of {@link WithdrawCreditResponse }
     * 
     */
    public WithdrawCreditResponse createWithdrawCreditResponse() {
        return new WithdrawCreditResponse();
    }

    /**
     * Create an instance of {@link SecurityFault }
     * 
     */
    public SecurityFault createSecurityFault() {
        return new SecurityFault();
    }

    /**
     * Create an instance of {@link WithdrawCredit }
     * 
     */
    public WithdrawCredit createWithdrawCredit() {
        return new WithdrawCredit();
    }

    /**
     * Create an instance of {@link FindResponse }
     * 
     */
    public FindResponse createFindResponse() {
        return new FindResponse();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link Pay }
     * 
     */
    public Pay createPay() {
        return new Pay();
    }

    /**
     * Create an instance of {@link Cancel }
     * 
     */
    public Cancel createCancel() {
        return new Cancel();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link Find }
     * 
     */
    public Find createFind() {
        return new Find();
    }

    /**
     * Create an instance of {@link Destination }
     * 
     */
    public Destination createDestination() {
        return new Destination();
    }

    /**
     * Create an instance of {@link Flight }
     * 
     */
    public Flight createFlight() {
        return new Flight();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "cancelResponse")
    public JAXBElement<CancelResponse> createCancelResponse(CancelResponse value) {
        return new JAXBElement<CancelResponse>(_CancelResponse_QNAME, CancelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FullFlightFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "FullFlightFault")
    public JAXBElement<FullFlightFault> createFullFlightFault(FullFlightFault value) {
        return new JAXBElement<FullFlightFault>(_FullFlightFault_QNAME, FullFlightFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "payResponse")
    public JAXBElement<PayResponse> createPayResponse(PayResponse value) {
        return new JAXBElement<PayResponse>(_PayResponse_QNAME, PayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithdrawCreditResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "withdrawCreditResponse")
    public JAXBElement<WithdrawCreditResponse> createWithdrawCreditResponse(WithdrawCreditResponse value) {
        return new JAXBElement<WithdrawCreditResponse>(_WithdrawCreditResponse_QNAME, WithdrawCreditResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "createResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "findResponse")
    public JAXBElement<FindResponse> createFindResponse(FindResponse value) {
        return new JAXBElement<FindResponse>(_FindResponse_QNAME, FindResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WithdrawCredit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "withdrawCredit")
    public JAXBElement<WithdrawCredit> createWithdrawCredit(WithdrawCredit value) {
        return new JAXBElement<WithdrawCredit>(_WithdrawCredit_QNAME, WithdrawCredit.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SecurityFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "SecurityFault")
    public JAXBElement<SecurityFault> createSecurityFault(SecurityFault value) {
        return new JAXBElement<SecurityFault>(_SecurityFault_QNAME, SecurityFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Pay }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "pay")
    public JAXBElement<Pay> createPay(Pay value) {
        return new JAXBElement<Pay>(_Pay_QNAME, Pay.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Cancel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "cancel")
    public JAXBElement<Cancel> createCancel(Cancel value) {
        return new JAXBElement<Cancel>(_Cancel_QNAME, Cancel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Find }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://reservation.service.bookingserver.aos.fel.cvut.cz/", name = "find")
    public JAXBElement<Find> createFind(Find value) {
        return new JAXBElement<Find>(_Find_QNAME, Find.class, null, value);
    }

}
