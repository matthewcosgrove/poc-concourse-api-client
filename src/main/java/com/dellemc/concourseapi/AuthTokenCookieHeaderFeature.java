package com.dellemc.concourseapi;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * Pattern taken from org.glassfish.jersey.client.oauth2.OAuth2ClientFeature
 * in groupId=org.glassfish.jersey.security and artifactId=oauth2-client
 * 
 * @author matthew.cosgrove
 *
 */
public class AuthTokenCookieHeaderFeature implements Feature {
	
	private final AuthTokenCookieHeaderSupportFilter filter;

    public AuthTokenCookieHeaderFeature(String accessToken) {
        this.filter = new AuthTokenCookieHeaderSupportFilter(accessToken);
    }

	@Override
	public boolean configure(FeatureContext context) {
		context.register(filter);
		return true;
	}

}
