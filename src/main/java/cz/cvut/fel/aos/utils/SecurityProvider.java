package cz.cvut.fel.aos.utils;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import java.security.MessageDigest;

/**
 * <p>Set of security utils.</p>
 *
 * @author Karel Cemus
 */
@Slf4j
@Stateless
public class SecurityProvider {

    private static final String STATIC_SALT = "1ac111078e603723e8b89a16252f0b25e7a7d9a8";

    public void verifyAccess( long id, String password, String passwordToVerify ) throws SecurityException {
        // verify access to reservation
        if ( !password.equalsIgnoreCase( hash( id, passwordToVerify ) ) ) {
            throw new SecurityException( String.format( "Access to reservation with ID '%d' is forbidden. Password is incorrect.", id ) );
        }
    }

    public String hash( String salt, String password ) {
        return hash( STATIC_SALT + hash( salt + password ) );
    }

    public String hash( final long salt, final String password ) {
        return hash( Long.toString( salt ), password );
    }

    private String hash( String message ) {
        try {
            // convert message to bytes
            byte[] bytesOfMessage = ( message ).getBytes( "UTF-8" );

            // hash it in bytes
            MessageDigest md = MessageDigest.getInstance( "SHA-1" );
            byte[] digest = md.digest( bytesOfMessage );

            // convert back to string in hex
            StringBuilder builder = new StringBuilder( 50 );
            for ( byte b : digest ) {
                builder.append( String.format( "%02x", b ) );
            }
            return builder.toString();

        } catch ( Exception e ) {
            SecurityProvider.log.error( "Password hashing failed." );
            throw new RuntimeException( e );
        }
    }
}
