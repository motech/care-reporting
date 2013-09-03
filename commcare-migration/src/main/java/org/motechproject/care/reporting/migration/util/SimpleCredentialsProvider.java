package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScheme;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.apache.commons.httpclient.auth.CredentialsProvider;

public class SimpleCredentialsProvider implements CredentialsProvider {
    private static final int MAX_TRIES = 1;

    private final String username;
    private final String password;

    private int tries;

    public SimpleCredentialsProvider(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Credentials getCredentials(AuthScheme scheme, String host, int port, boolean proxy) throws CredentialsNotAvailableException {
        if(tries >= MAX_TRIES) {
            throw new CredentialsNotAvailableException("Known credential has already been rejected.");
        }
        tries++;
        return new UsernamePasswordCredentials(username, password);
    }
}
