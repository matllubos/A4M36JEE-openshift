package cz.cvut.fel.init;

import cz.cvut.fel.util.DatabaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static cz.cvut.fel.util.ArquillianDataProvider.provide;

/**
 * <p>Removes all data from the database.</p>
 *
 * @author Karel Cemus
 */
@Slf4j
@Test( groups = "initialization" )
public class InitializeEmptyDatabaseTest extends DatabaseTest {

    @Test( dataProvider = "truncateTableProvider" )
    public void truncateTable( final String table ) {

        String query = String.format( "TRUNCATE TABLE %s RESTART IDENTITY CASCADE", table );

        log.trace( "Truncating table '{}'.", table );
        em.createNativeQuery( query ).executeUpdate();
    }

    @DataProvider
    public Object[][] truncateTableProvider() {
        return provide(
                "InitializeEmptyDatabaseTest#truncateTableProvider",
                new Object[][]{
                        new Object[]{ "payment" },
                        new Object[]{ "reservation" },
                        new Object[]{ "flight" },
                        new Object[]{ "destination" }
                } );
    }
}
