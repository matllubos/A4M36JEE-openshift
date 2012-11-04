package cz.cvut.fel.aos.service;

import org.apache.cxf.tools.wsdlto.WSDLToJava;

/** @author Karel Cemus */
public class CodeGenerator {

    public static void main( String[] args ) {
        WSDLToJava.main( new String[]{
                "-client",
//                "-autoNameResolution",
                "-d",
                "src/main/java",
                "-b",
                "src/main/resources/jaxws-bindings.xml",
                "-p",
                "cz.cvut.fel.aos.service.facade",
                "http://localhost:8080/InterfaceServer/FacadeService?wsdl"
        } );
        System.out.println( "Done!" );
    }
}
