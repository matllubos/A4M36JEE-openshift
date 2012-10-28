
/*
 * 
 */

package cz.cvut.fel.aos.booking.service.reservation;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.3.1
 * Wed Oct 24 21:34:41 CEST 2012
 * Generated source version: 2.3.1
 * 
 */


@WebServiceClient(name = "ReservationServiceImplService", 
                  wsdlLocation = "http://localhost:8080/BookingServer/ReservationService?wsdl",
                  targetNamespace = "http://reservation.service.booking.aos.fel.cvut.cz/")
public class ReservationServiceImplService extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://reservation.service.booking.aos.fel.cvut.cz/", "ReservationServiceImplService");
    public final static QName ReservationServiceImplPort = new QName("http://reservation.service.booking.aos.fel.cvut.cz/", "ReservationServiceImplPort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/BookingServer/ReservationService?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://localhost:8080/BookingServer/ReservationService?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public ReservationServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ReservationServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ReservationServiceImplService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     * 
     * @return
     *     returns ReservationService
     */
    @WebEndpoint(name = "ReservationServiceImplPort")
    public ReservationService getReservationServiceImplPort() {
        return super.getPort(ReservationServiceImplPort, ReservationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ReservationService
     */
    @WebEndpoint(name = "ReservationServiceImplPort")
    public ReservationService getReservationServiceImplPort(WebServiceFeature... features) {
        return super.getPort(ReservationServiceImplPort, ReservationService.class, features);
    }

}