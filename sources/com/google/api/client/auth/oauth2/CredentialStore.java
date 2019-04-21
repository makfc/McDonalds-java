package com.google.api.client.auth.oauth2;

import com.google.api.client.util.Beta;
import java.io.IOException;

@Beta
@Deprecated
public interface CredentialStore {
    void store(String str, Credential credential) throws IOException;
}
