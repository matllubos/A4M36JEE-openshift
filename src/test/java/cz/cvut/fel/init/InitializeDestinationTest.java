package cz.cvut.fel.init;

import cz.cvut.fel.model.Destination;
import cz.cvut.fel.util.DatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static cz.cvut.fel.util.ArquillianDataProvider.provide;
import static org.testng.Assert.*;

/** @author Karel Cemus */
@Slf4j
@Test( groups = "initialization", dependsOnMethods = "cz.cvut.fel.init.InitializeEmptyDatabaseTest.clearAllDestinations" )
public class InitializeDestinationTest extends DatabaseTest {

    @Test( dataProvider = "destinationProvider" )
    public void insert( String code, String name ) {

        Destination destination = new Destination();
        destination.setCode( code );
        destination.setName( name );

        log.trace( "Saving '{}'", destination );
        em.persist( destination );

        assertFalse( destination.getId() == 0 );
    }

    @DataProvider
    public Object[][] destinationProvider() {
        return provide(
                "InitializeDestinationTest#destinationProvider",
                new Object[][]{
                        new Object[]{ "PRG", "Prague" },
                        new Object[]{ "MAD", "Madrid" },
                        new Object[]{ "LHR", "London (Heathrow)" },
                        new Object[]{ "INN", "Innsburck" },
                        new Object[]{ "VIE", "Vienna" }
                } );
    }
}
