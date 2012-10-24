package cz.cvut.fel.aos;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

/** @author Karel Cemus */
public class CodeGenerator {

    public static void main( String[] args ) {
        WSDLToJava.main( new String[]{
                "-client",
                "-d",
                "src/main/java",
                "-b",
                "src/main/resources/jaxws-bindings.xml",
                "-p",
                "cz.cvut.fel.aos.service.flight",
                "http://localhost:8080/BookingServer/FlightService?wsdl"
        } );
        System.out.println( "Done!" );
    }
}
