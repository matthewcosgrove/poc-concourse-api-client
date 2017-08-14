package config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;

/**
 * Kept separate from other packages to avoid the component scan picking up this config and applying to all Feign clients.
 * 
 * By keeping it separate it will only be picked up by @FeignClient which explicitly specifies it via the configuration attribute
 *
 */
@Configuration
public class ConcourseBasicAuthConfiguration {
	
	@Value("${http.basic.auth.username}")
	String username;
	
	@Value("${http.basic.auth.password}")
	String password;

	@Bean
	public RequestInterceptor opsManBasicAuthInterceptor() {
		return new BasicAuthRequestInterceptor(username, password);
	}
	
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
	@Bean
	public RequestInterceptor skipSslValidationInterceptor() {
		return new RequestInterceptor() {
			
			@Override
			public void apply(RequestTemplate template) {
				boolean isSkipSslValidation = true;
	    		if(isSkipSslValidation ){
	        		SslVerificationUtils.dontDoThisAtHome();
	        	}
			}
		};
	}
}