package org.netbeans.saas.facebook;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.netbeans.saas.RestConnection;

/**
 *
 * @author lukas & Jan
 */
public class FacebookSocialNetworkingServiceAuthenticator {

    private static String apiKey;
    private static String secret;
    public static String sessionKey;
    private static final String PROP_FILE = FacebookSocialNetworkingServiceAuthenticator.class.getSimpleName().toLowerCase() + ".properties";

    static {
        try {
            Properties props = new Properties();
            props.load( FacebookSocialNetworkingServiceAuthenticator.class.getResourceAsStream( PROP_FILE ) );
            apiKey = props.getProperty( "api_key" );
            secret = props.getProperty( "secret" );
        } catch ( IOException ex ) {
            Logger.getLogger( FacebookSocialNetworkingServiceAuthenticator.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    public static String getApiKey() throws IOException {
        if ( apiKey == null || apiKey.length() == 0 ) {
            throw new IOException( "Please specify your api key and secret in the " + PROP_FILE + " file." );
        }
        return apiKey;
    }

    public static String getSessionKey() throws IOException {
        if ( sessionKey == null || sessionKey.length() == 0 ) {
            throw new IOException( "Failed to get a valid session key." );
        }
        return sessionKey;
    }

    private static String getSecret() throws IOException {
        if ( secret == null || secret.length() == 0 ) {
            throw new IOException( "Please specify your secret in the " + PROP_FILE + " file." );
        }
        return secret;
    }

    public static void login() throws IOException {
        if ( sessionKey == null ) {
            String token = getToken();

            String method = "facebook.auth.getSession";
            String v = "1.0";
            String apiKey2 = getApiKey();
            String secret2 = getSecret();

            String sig = sign( secret2,
                new String[][]{
                    { "method", method },
                    { "v", v },
                    { "api_key", apiKey2 },
                    { "auth_token", token }
                } );

            RestConnection conn = new RestConnection(
                "http://api.facebook.com/restserver.php",
                new String[][]{
                    { "method", method },
                    { "api_key", apiKey2 },
                    { "sig", sig },
                    { "v", v },
                    { "auth_token", token }
                } );

            String result = conn.get().getDataAsString();

            try {
                sessionKey = result.substring( result.indexOf( "<session_key>" ) + 13,
                    result.indexOf( "</session_key>" ) );
            } catch ( Exception ex ) {
                throw new IOException( "Failed to get session key and secret: " + result );
            }
        }
    }

    private static void logout() {
    }

    private static String getToken() throws IOException {
        String token = null;
        String method = "facebook.auth.createToken";
        String v = "1.0";
        String apiKey2 = getApiKey();
        String secret2 = getSecret();

        String sig = sign( secret2,
            new String[][]{
                { "method", method },
                { "api_key", apiKey2 },
                { "v", v }
            } );

        RestConnection conn = new RestConnection(
            "http://api.facebook.com/restserver.php",
            new String[][]{
                { "method", method },
                { "api_key", apiKey2 },
                { "sig", sig },
                { "v", v }
            } );
        String result = conn.get().getDataAsString();

        try {
            token = result.substring( result.indexOf( "<auth_createToken_response" ),
                result.indexOf( "</auth_createToken_response>" ) );
            token = token.substring( token.indexOf( ">" ) + 1 );
        } catch ( Exception ex ) {
            throw new IOException( "Failed to get session token: " + result );
        }

        String loginUrl = "http://www.facebook.com/login.php?api_key="
            + apiKey2 + "&v=" + v + "&auth_token=" + token;

        if ( JOptionPane.showInputDialog( null,
            "Please log into your Facebook account using the following URL to authorize this application and click OK after you are done:",
            "Facebook Authorization Dialog",
            JOptionPane.INFORMATION_MESSAGE,
            null,
            null,
            loginUrl ) == null ) {
            throw new IOException( "Authorizatoin declined" );
        }

        return token;
    }

    public static String sign( String[][] params ) throws IOException {
        return sign( secret, params );
    }

    private static String sign( String secret, String[][] params ) throws IOException {
        try {
            TreeMap<String, String> map = new TreeMap<String, String>();

            for ( int i = 0; i
                < params.length; i++ ) {
                String key = params[i][0];
                String value = params[i][1];

                if ( value != null ) {
                    map.put( key, value );
                }
            }

            String signature = "";
            Set<Map.Entry<String, String>> entrySet = map.entrySet();
            for ( Map.Entry<String, String> entry : entrySet ) {
                signature += entry.getKey() + "=" + entry.getValue();
            }

            signature += secret;

            MessageDigest md = MessageDigest.getInstance( "MD5" );
            byte[] sum = md.digest( signature.getBytes( "UTF-8" ) );
            BigInteger bigInt = new BigInteger( 1, sum );

            return bigInt.toString( 16 );
        } catch ( Exception ex ) {
            throw new RuntimeException( ex );
        }
    }
}
