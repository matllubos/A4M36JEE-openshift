package cz.cvut.fel.aos.bookingserver.insert;

import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.hibernate.ejb.EntityManagerImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * <p>Rodičovská třída zajišťující inicializaci a uzavření testu do transakce</p>
 *
 * @author Karel Cemus
 */
public class DatabaseTest {

    private EntityManagerFactory emf;

    protected EntityManager em;

    @BeforeClass
    public void setUp() {
        emf = Persistence.createEntityManagerFactory( "flightSystemPersistenceTest" );
        em = emf.createEntityManager();
    }

    @AfterClass
    public void tearDown() {
        em.close();
        emf.close();
    }

    @BeforeMethod
    public void setUpMethod() {
        em.getTransaction().begin();
    }

    @AfterMethod
    public void tearDownMethod() {
        em.getTransaction().commit();
    }
}
