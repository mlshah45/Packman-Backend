package com.packman.Util;

import com.google.api.client.auth.openidconnect.IdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mlshah on 4/26/16.
 */
public class GoogleAuth {

    private final GoogleIdTokenVerifier mVerifier;
    private final JsonFactory mJFactory;
    private String mProblem = "Verification failed. (Time-out?)";
    HttpTransport transport;

    public IdToken.Payload verifyToken(String tokenString) {

        GoogleIdToken.Payload payload = null;
        try {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, mJFactory)
                .setAudience(Arrays.asList(Constants.ANDROID_CLIENT_AUTH_ID))
                // If you retrieved the token on Android using the Play Services 8.3 API or newer, set
                // the issuer to "https://accounts.google.com". Otherwise, set the issuer to
                // "accounts.google.com". If you need to verify tokens from multiple sources, build
                // a GoogleIdTokenVerifier for each issuer and try them both.
                .setIssuer("https://accounts.google.com")
                .build();

        GoogleIdToken idToken = verifier.verify(tokenString);
        if (idToken != null) {
            payload = idToken.getPayload();
        }
        } catch (GeneralSecurityException e) {
            System.out.println("exception " + e);
                mProblem = "Security issue: " + e.getLocalizedMessage();
        } catch (IOException e) {
            System.out.println("exception erere " + e);
                mProblem = "Network problem: " + e.getLocalizedMessage();
        }
        catch(Exception e) {
            System.out.println("exception lastone " + e);
        }

        return payload;

    }

    public GoogleAuth() {
        transport = new NetHttpTransport();
        mJFactory = new JacksonFactory();
        mVerifier = new GoogleIdTokenVerifier(transport, mJFactory);
    }

    public String problem() {
        return mProblem;
    }
}
