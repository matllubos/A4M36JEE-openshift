package cz.cvut.fel.service;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.util.ArquillianTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.*;

import static cz.cvut.fel.util.ArquillianDataProvider.provide;
import static java.lang.Thread.sleep;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Slf4j
@Test( groups = "services" )
public class DestinationServiceTest extends ArquillianTest {

    @Inject
    private DestinationService service;

    @AfterMethod
    public void cleanUp() throws Exception {
        if ( isInContainer() ) {
            transaction.begin();
            em.createQuery( "DELETE FROM Destination d WHERE d.code = 'ABC'" ).executeUpdate();
            transaction.commit();
        }
    }

    @Test( dataProvider = "findByCodeProvider" )
    public void testFindByCode( String code, boolean exists ) {
        try {
            service.findByCode( code );
            if ( !exists ) fail( "Destination is supposed not to exist." );
        } catch ( javax.ejb.EJBException ignored ) {
            if ( exists ) {
                fail( "Destination is supposed to exist." );
            } else if ( !( ignored.getCause() instanceof NoSuchDestinationException ) ) {
                fail( "It is supposed to throw NoSuchDestinationException." );
            }
        }
    }

    @DataProvider
    public Object[][] findByCodeProvider() {
        return provide(
                "DestinationServiceTest#findByCodeProvider",
                new Object[][]{
                        new Object[]{ "PRG", true },
                        new Object[]{ "MAD", true },
                        new Object[]{ "LHR", true },
                        new Object[]{ "INN", true },
                        new Object[]{ "VIE", true },
                        new Object[]{ "XXX", false },
                        new Object[]{ "RUS", false },
                } );
    }

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

    @Test
    public void testCreate() {
        Destination destination = new Destination();
        destination.setCode( "ABC" );
        destination.setName( "Alphabetic" );

        // set validity for 5 minutes, after that the instance gets invalid
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MINUTE, 2 );
        destination.setValidUntil( calendar.getTime() );

        // perform test
        destination = service.saveDestination( destination );

        // verify results
        assertTrue( destination.getId() > 0 );
        destination = service.findByCode( "ABC" );
        assertEquals( destination.getCode(), "ABC" );
        assertEquals( destination.getName(), "Alphabetic" );
    }

    @Test( dependsOnMethods = "testCreate" )
    public void testUpdate() {

        // create test instance
        testCreate();

        // update model
        Destination destination = service.findByCode( "ABC" );
        destination.setName( "Non-Alphabetic" );

        // perform test
        service.saveDestination( destination );

        // verify results
        assertTrue( destination.getId() > 0 );
        destination = service.findByCode( "ABC" );
        assertEquals( destination.getCode(), "ABC" );
        assertEquals( destination.getName(), "Non-Alphabetic" );
    }

    @Test( dependsOnMethods = "testCreate", expectedExceptions = javax.ejb.EJBException.class )
    public void testDelete() throws InterruptedException {

        // create test instance
        testCreate();

        Destination destination = service.findByCode( "ABC" );

        // perform test
        service.deleteDestination( destination.getId() );

        // wait for new timestamp
        sleep( 1000 );

        // verify results, it is supposed not to exist - it should throw an exception
        service.findByCode( "ABC" );
    }
}
