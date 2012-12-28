package cz.cvut.fel.util;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

/**
 * <p>Parent test class encapsulating the test invocation into the transaction</p>
 *
 * @author Karel Cemus
 */
public class DatabaseTest extends ArquillianTest {

    @PersistenceContext
    protected EntityManager em;

    @Resource
    private UserTransaction transaction;

    @BeforeMethod
    public void setUpMethod() throws Exception {

        if ( isInContainer() ) {
            transaction.begin();
        }
    }

    @AfterMethod( alwaysRun = true )
    public void tearDownMethod( ITestResult result ) throws Exception {

        if ( isInContainer() ) {
            if ( result.getStatus() == ITestResult.SUCCESS ) {
                transaction.commit();
            } else { // if test failed then rollback
                transaction.rollback();
            }
        }
    }
}
