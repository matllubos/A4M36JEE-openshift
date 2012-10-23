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
                    "cz.cvut.fel.aos.service.destination",
                    "http://localhost:8080/BookingServer/DestinationService?wsdl"
                } );
        System.out.println( "Done!" );
    }
}
