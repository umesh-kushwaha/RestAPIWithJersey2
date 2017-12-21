package com.umesh.rest.api.security;

import org.apache.tomcat.util.codec.binary.Base64;

public class AuthService {

	private static final String USER_NAME = "UMESH";
	private static final String PASSWORD  = "PASSWORD";
	
	public static boolean authenticate(String authorizationToken) {

		  if (authorizationToken == null)
		   return false;
		  String authToken = authorizationToken.replace("Basic", "").trim();
		  String[] authPair = decode(authToken);
		  if (authPair[0].equals(USER_NAME) && authPair[1].equals(PASSWORD))
		   return true;
		  return false;
	}
	
	private static String[] decode(final String encoded) {
        final byte[] decodedBytes 
                = Base64.decodeBase64(encoded.getBytes());
        final String pair = new String(decodedBytes);
        final String[] userDetails = pair.split(":", 2);
        return userDetails;
    }
 
    private static String createEncodedText(final String username, 
                                            final String password) {
        final String pair = "Basic "+username + ":" + password;
        final byte[] encodedBytes = Base64.encodeBase64(pair.getBytes());
        return new String(encodedBytes);
    }
}
