package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.booking.service.destination.Destination;
import cz.cvut.fel.aos.booking.service.destination.DestinationService;
import cz.cvut.fel.aos.booking.service.destination.DestinationServiceImplService;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = true )
public class DestinationTest {

    @Test
    public void testFindAll() {

        DestinationServiceImplService factory = new DestinationServiceImplService( DestinationServiceImplService.WSDL_LOCATION, DestinationServiceImplService.SERVICE );
        DestinationService service = factory.getDestinationServiceImplPort();
        Collection<Destination> destinations = service.findAll();

        assertEquals( destinations.size(), 5 );

        Set<String> codes = new HashSet<String>();
        for ( Destination destination : destinations ) {
            codes.add( destination.getCode() );
        }

        assertEquals( codes, new HashSet<String>( Arrays.asList( new String[]{ "PRG", "MAD", "LHR", "INN", "VIE" } ) ) );

    }
}
