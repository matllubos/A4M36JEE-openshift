package cz.cvut.fel.aos.service;

import cz.cvut.fel.aos.model.Destination;
import cz.cvut.fel.aos.service.destination.DestinationService;
import cz.cvut.fel.util.ArquillianTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

/** @author Karel Cemus */
@Slf4j
public class DestinationTest extends ArquillianTest {

    @Inject
    private DestinationService service;

    @Test
    public void testFindAll() {
        Collection<Destination> destinations = service.findAllDestinations();

        assertEquals( destinations.size(), 5 );

        Set<String> codes = new HashSet<String>();
        for ( Destination destination : destinations ) {
            codes.add( destination.getCode() );
        }

        log.trace( "Found destinations: '{}'.", codes );
        assertEquals( codes, new HashSet<String>( Arrays.asList( new String[]{ "PRG", "MAD", "LHR", "INN", "VIE" } ) ) );
    }
}
