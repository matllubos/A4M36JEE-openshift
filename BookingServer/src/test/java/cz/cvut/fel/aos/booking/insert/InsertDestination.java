package cz.cvut.fel.aos.booking.insert;

import cz.cvut.fel.aos.booking.model.Destination;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

/** @author Karel Cemus */
@Test( enabled = false )
public class InsertDestination extends DatabaseTest {

    @Test
    public void clearAll() {
        List<Destination> destinations = em.createNamedQuery( "Destination.findAll", Destination.class ).getResultList();

        for ( Destination destination : destinations )
            em.remove( destination );
    }

    @Test( dataProvider = "destinationProvider", dependsOnMethods = "clearAll" )
    public void run( String code, String name ) {

        Destination destination = new Destination();
        destination.setCode( code );
        destination.setName( name );

        em.persist( destination );

        assertFalse( destination.getId() == 0 );
    }


    @DataProvider
    public String[][] destinationProvider() {
        return new String[][]{
                new String[]{ "PRG", "Prague" },
                new String[]{ "MAD", "Madrid" },
                new String[]{ "LHR", "London (Heathrow)" },
                new String[]{ "INN", "Innsburck" },
                new String[]{ "VIE", "Vienna" }
        };
    }
}
