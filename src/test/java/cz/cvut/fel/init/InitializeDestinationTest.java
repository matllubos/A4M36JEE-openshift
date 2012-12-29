package cz.cvut.fel.init;

import cz.cvut.fel.aos.model.Destination;
import cz.cvut.fel.util.DatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/** @author Karel Cemus */
@Slf4j
@Test( groups = "initialization" )
public class InitializeDestinationTest extends DatabaseTest {

    @Test
    public void clearAll() {

        InitializeDestinationTest.log.info( "Clearing all destinations in the database." );

        List<Destination> destinations = em.createNamedQuery( "Destination.findAll", Destination.class ).getResultList();

        for ( Destination destination : destinations ) {
            InitializeDestinationTest.log.info( "Removing '{}'.", destination );
            em.remove( destination );
        }
    }

    @Test( dataProvider = "destinationProvider", dependsOnMethods = "clearAll" )
    public void run( String code, String name ) {

        Destination destination = new Destination();
        destination.setCode( code );
        destination.setName( name );

        InitializeDestinationTest.log.info( "Saving '{}'", destination );
        em.persist( destination );

        assertFalse( destination.getId() == 0 );
    }

    private static int destinationProviderCounter = 0;

    @DataProvider
    public String[][] destinationProvider() {
        String[][] data = new String[][]{
                new String[]{ "PRG", "Prague" },
                new String[]{ "MAD", "Madrid" },
                new String[]{ "LHR", "London (Heathrow)" },
                new String[]{ "INN", "Innsburck" },
                new String[]{ "VIE", "Vienna" }
        };

        if ( isInContainer() ) {
            return new String[][]{ data[ destinationProviderCounter++ ] };
        } else {
            return data;
        }
    }
}
