package cz.cvut.fel.service;


import cz.cvut.fel.exception.NoSuchDestinationException;
import cz.cvut.fel.model.Destination;
import cz.cvut.fel.util.ArquillianTest;
import lombok.extern.slf4j.Slf4j;
import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;
import org.testng.annotations.*;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.validation.ConstraintViolationException;

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

    @BeforeClass
    public void login() throws Exception {
        login("peter", "peter");
    }

    @AfterClass
    public void logout() throws Exception {
        final SecurityClient securityClient = SecurityClientFactory.getSecurityClient();
        securityClient.logout();
    }

    @Test( dataProvider = "findByCodeProvider")
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
                        new Object[]{ "RUS", false }
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
        // perform test
        Destination destination = service.save( createDestination() );

        // verify results
        assertTrue( destination.getId() > 0 );
        destination = service.findByCode( "ABC" );
        assertEquals( destination.getCode(), "ABC" );
        assertEquals( destination.getName(), "Alphabetic" );
    }

    @Test
    public void testCreateInvalid() {
        try {
            Destination destination = new Destination();
            destination.setCode( "LONG" ); // code is too long
            destination.setName( "Destination with long name" );

            // set validity for 5 minutes, after that the instance gets invalid
            Calendar calendar = Calendar.getInstance();
            calendar.add( Calendar.MINUTE, 2 );
            destination.setValidUntil( calendar.getTime() );

            // perform test
            service.save( destination );
            fail( "Instance is invalid, it supposed to throw validation exception." );
        } catch ( EJBException ex ) {
            if ( !( ex.getCause() instanceof ConstraintViolationException ) ) {
                fail( "Expected validation exception." );
            }
        }
    }

    @Test( dependsOnMethods = "testCreate" )
    public void testUpdate() throws LoginException {

        // create test instance
        testCreate();

        // update model
        Destination destination = service.findByCode( "ABC" );
        destination.setName( "Non-Alphabetic" );

        // perform test
        service.save( destination );

        // verify results
        assertTrue( destination.getId() > 0 );
        destination = service.findByCode( "ABC" );
        assertEquals( destination.getCode(), "ABC" );
        assertEquals( destination.getName(), "Non-Alphabetic" );
    }

    @Test( dependsOnMethods = "testCreate", expectedExceptions = javax.ejb.EJBException.class )
    public void testDelete() throws InterruptedException, LoginException {

        // create test instance
        testCreate();

        Destination destination = service.findByCode( "ABC" );

        // perform test
        service.delete( destination.getId() );

        // wait for new timestamp
        sleep( 1000 );

        // verify results, it is supposed not to exist - it should throw an exception
        service.findByCode( "ABC" );
    }


    @DataProvider
    public Object[][] usersProvider() {
         return new Object[][]{
             new Object[]{ "peter", "peter", true },
             new Object[]{ "marcus", "marcus", false },
         };
    }

    @Test( dependsOnMethods = "testDelete", dataProvider = "usersProvider" )
    public void testCreateWithUser(String username, String password, boolean allowed) throws Exception {
        logout();

        login(username, password);
        try {
            service.save( createDestination() );
            assertTrue(allowed);
        } catch (javax.ejb.EJBAccessException ex)  {
            assertFalse(allowed);
        }
    }


    @Test( dataProvider = "usersProvider")
    public void testDeleteWithUser(String username, String password, boolean allowed ) throws Exception {

        // create test instance
        testCreate();
        logout();

        login(username, password);
        Destination destination = service.findByCode( "ABC" );

        try {
            service.delete( destination.getId() );
            assertTrue(allowed);
        } catch (javax.ejb.EJBAccessException ex)  {
            assertFalse(allowed);
        }

    }

    @Test( dependsOnMethods = "testCreate", expectedExceptions = javax.ejb.EJBAccessException.class )
    public void testDeleteWithoutUser() throws Exception {

        // create test instance
        testCreate();
        logout();

        Destination destination = service.findByCode( "ABC" );

        // this should throw an EJBAccessException
        service.delete( destination.getId() );
    }

    @Test( expectedExceptions = javax.ejb.EJBAccessException.class )
    public void testCreateWithoutUser() throws Exception {
        logout();

        // this should throw an EJBAccessException
        service.save(createDestination());
    }


    private Destination createDestination() {
        Destination destination = new Destination();
        destination.setCode( "ABC" );
        destination.setName( "Alphabetic" );

        // set validity for 5 minutes, after that the instance gets invalid
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MINUTE, 2 );
        destination.setValidUntil( calendar.getTime() );
        return destination;
    }

    private void login(String username, String password) throws Exception {
        final SecurityClient securityClient = SecurityClientFactory.getSecurityClient();
        securityClient.setSimple(username, password);
        securityClient.login();
    }

}
