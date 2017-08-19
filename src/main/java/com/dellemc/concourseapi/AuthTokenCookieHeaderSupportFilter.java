package com.dellemc.concourseapi;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;

@Priority(Priorities.AUTHENTICATION)
public class AuthTokenCookieHeaderSupportFilter implements ClientRequestFilter {
    
    private final String accessToken;

    /**
     * Create a new filter with predefined access token.
     *
     * @param accessToken Access token.
     */
    public AuthTokenCookieHeaderSupportFilter(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void filter(ClientRequestContext request) throws IOException {
        String authentication = "Bearer " + accessToken;
        request.getHeaders().add(HttpHeaders.COOKIE, "ATC-Authorization=" + authentication);
    }
}

