package cz.cvut.fel.utils;

/**
 * <p>Set of security utils.</p>
 *
 * @author Karel Cemus
 */
public interface SecurityProvider {

    void verifyAccess( long id, String password, String passwordToVerify ) throws SecurityException;

    String hash( String salt, String password );

    String hash( long salt, String password );
}
