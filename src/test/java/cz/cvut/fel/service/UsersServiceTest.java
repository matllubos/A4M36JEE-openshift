package cz.cvut.fel.service;

import cz.cvut.fel.util.ArquillianTest;
import org.jboss.security.client.SecurityClient;
import org.jboss.security.client.SecurityClientFactory;
import org.testng.annotations.BeforeGroups;

/**
 * Created with IntelliJ IDEA.
 * User: lubos
 * Date: 18.01.13
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public class UsersServiceTest extends ArquillianTest {

    @BeforeGroups( groups = "user-admin" )
    public void loginFlightManager() throws Exception {
        login("peter", "peter");
    }

    @BeforeGroups( groups = "user-flight-manager" )
    public void loginAdmin() throws Exception {
        login("marcus", "marcus");
    }


    private void login(String username, String password) throws Exception {
        final SecurityClient securityClient = SecurityClientFactory.getSecurityClient();
        securityClient.setSimple(username, password);
        securityClient.login();
    }

}
