/*
*
*/

package cz.cvut.fel.aos.service.flight;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/** This class was generated by Apache CXF 2.3.1 Wed Oct 24 00:32:41 CEST 2012 Generated source version: 2.3.1 */


@WebServiceClient( name = "FlightServiceImplService",
        wsdlLocation = "http://localhost:8080/BookingServer/FlightService?wsdl",
        targetNamespace = "http://flight.service.bookingserver.aos.fel.cvut.cz/" )
public class FlightServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName( "http://flight.service.bookingserver.aos.fel.cvut.cz/", "FlightServiceImplService" );

    public final static QName FlightServiceImplPort = new QName( "http://flight.service.bookingserver.aos.fel.cvut.cz/", "FlightServiceImplPort" );

    static {
        URL url = null;
        try {
            url = new URL( "http://localhost:8080/BookingServer/FlightService?wsdl" );
        } catch ( MalformedURLException e ) {
            System.err.println( "Can not initialize the default wsdl from http://localhost:8080/BookingServer/FlightService?wsdl" );
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public FlightServiceImplService( URL wsdlLocation ) {
        super( wsdlLocation, SERVICE );
    }

    public FlightServiceImplService( URL wsdlLocation, QName serviceName ) {
        super( wsdlLocation, serviceName );
    }

    public FlightServiceImplService() {
        super( WSDL_LOCATION, SERVICE );
    }


    /** @return returns FlightService */
    @WebEndpoint( name = "FlightServiceImplPort" )
    public FlightService getFlightServiceImplPort() {
        return super.getPort( FlightServiceImplPort, FlightService.class );
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will
     *                 have their default values.
     *
     * @return returns FlightService
     */
    @WebEndpoint( name = "FlightServiceImplPort" )
    public FlightService getFlightServiceImplPort( WebServiceFeature... features ) {
        return super.getPort( FlightServiceImplPort, FlightService.class, features );
    }

}
