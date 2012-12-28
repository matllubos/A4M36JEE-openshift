package cz.cvut.fel.util;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * <p>Parent class for all arquillian tests. It takes care of proper test package initialization.</p>
 *
 * @author Karel Cemus
 */
public class ArquillianTest extends Arquillian {

    @Deployment
    public static Archive<?> getDeployment() {
        return ShrinkWrap.create( WebArchive.class, "web.war" )
                // definition of persistence unit used in tests
                .addAsResource( "test-persistence.xml", "META-INF/persistence.xml" )
                // define the data-source with testing database
                .addAsWebInfResource( "jboss-test-ds.xml", "jboss-test-ds.xml" )
                // define the unnecessary configuration
                .addAsWebInfResource( EmptyAsset.INSTANCE, "beans.xml" )
                // logging configuration
                .addAsResource( "log4j.xml", "log4j.xml" )
                // add all classes in the project
                .addPackages( true, "cz.cvut.fel" );
    }
}
