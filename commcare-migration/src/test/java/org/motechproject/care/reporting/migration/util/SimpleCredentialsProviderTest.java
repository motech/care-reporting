package org.motechproject.care.reporting.migration.util;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.CredentialsNotAvailableException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class SimpleCredentialsProviderTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void shouldProviderUsernameAndPasswordCredentials() throws CredentialsNotAvailableException {
        SimpleCredentialsProvider credentialsProvider = new SimpleCredentialsProvider("myusername", "mypassword");
        Credentials credentials = credentialsProvider.getCredentials(null, null, 0, false);

        assertTrue(credentials instanceof UsernamePasswordCredentials);

        assertEquals("myusername", ((UsernamePasswordCredentials) credentials).getUserName());
        assertEquals("mypassword", ((UsernamePasswordCredentials) credentials).getPassword());
    }

    @Test
    public void shouldThrowExceptionIfCredentialsAreAskedTwice() throws CredentialsNotAvailableException {
        SimpleCredentialsProvider credentialsProvider = new SimpleCredentialsProvider("myusername", "mypassword");
        int maxTries = 1;
        int tries = 0;
        while(true) {
            try {
                credentialsProvider.getCredentials(null, null, 0, false);
                tries++;
            } catch (CredentialsNotAvailableException e) {
                assertEquals("Known credential has already been rejected.", e.getMessage());
                assertEquals(maxTries, tries);
                break;
            }

            if(tries >= 1000) {
                fail("Should not retry credentials for ever.");
            }
        }
    }
}
