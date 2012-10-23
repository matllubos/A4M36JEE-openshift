package cz.cvut.fel.aos;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

/**
 *
 * @author Karel Cemus
 */
public class CodeGenerator {

    public static void main( String[] args ) {
        WSDLToJava.main( new String[]{
                    "-client",
                    "-d",
                    "src/main/java",
                    "-p",
                    "cz.cvut.fel.aos.bookingserver.service.flight",
                    "http://localhost:8080/BookingServer/FlightService?wsdl"
                } );
        System.out.println( "Done!" );
    }
}
