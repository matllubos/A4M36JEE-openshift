/*
*
*/

package cz.cvut.fel.aos.service.destination;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/** This class was generated by Apache CXF 2.3.1 Sun Nov 04 12:34:38 CET 2012 Generated source version: 2.3.1 */


@WebServiceClient( name = "DestinationServiceImplService",
        wsdlLocation = "http://localhost:8080/DestinationService?wsdl",
        targetNamespace = "http://destination.service.aos.fel.cvut.cz/" )
public class DestinationServiceImplService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName( "http://destination.service.aos.fel.cvut.cz/", "DestinationServiceImplService" );

    public final static QName DestinationServiceImplPort = new QName( "http://destination.service.aos.fel.cvut.cz/", "DestinationServiceImplPort" );

    static {
        URL url = null;
        try {
            url = new URL( "http://localhost:8080/DestinationService?wsdl" );
        } catch ( MalformedURLException e ) {
            System.err.println( "Can not initialize the default wsdl from http://localhost:8080/DestinationService?wsdl" );
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public DestinationServiceImplService( URL wsdlLocation ) {
        super( wsdlLocation, SERVICE );
    }

    public DestinationServiceImplService( URL wsdlLocation, QName serviceName ) {
        super( wsdlLocation, serviceName );
    }

    public DestinationServiceImplService() {
        super( WSDL_LOCATION, SERVICE );
    }


    /** @return returns DestinationService */
    @WebEndpoint( name = "DestinationServiceImplPort" )
    public DestinationService getDestinationServiceImplPort() {
        return super.getPort( DestinationServiceImplPort, DestinationService.class );
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will
     *                 have their default values.
     *
     * @return returns DestinationService
     */
    @WebEndpoint( name = "DestinationServiceImplPort" )
    public DestinationService getDestinationServiceImplPort( WebServiceFeature... features ) {
        return super.getPort( DestinationServiceImplPort, DestinationService.class, features );
    }

}
