package cz.cvut.fel.util;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>TestNG data provider with Arquillian hack.</p>
 *
 * @author Karel Cemus
 */
public class ArquillianDataProvider {

    /** static counter of call to always provide proper test case */
    private static Map<String, Integer> calls = new HashMap<String, Integer>();

    private ArquillianDataProvider() {
        // only static calls
    }

    public static Object[][] provide( final String provider, final Object[][] data ) {

        // initialize map if not used yet
        if ( !calls.containsKey( provider ) ) calls.put( provider, 0 );


        if ( ArquillianTest.isInContainer() ) {
            // get instance and increase calls counter
            Object[][] testCase = new Object[][]{ data[ calls.get( provider ) ] };
            calls.put( provider, calls.get( provider ) + 1 );
            return testCase;

        } else {
            // return full set if called by client
            return data;
        }
    }
}
