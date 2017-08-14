package com.dellemc.concourseapi;

import org.springframework.cloud.netflix.feign.FeignClient;

import config.feign.ConcourseBasicAuthConfiguration;
import feign.RequestLine;

//curl -s -H "Authorization: Basic <base64 u+p>" http://<concourse>/api/v1/teams/zero/auth/token
@FeignClient(name = "AuthClient", url = "${concourse.host.url}", configuration = ConcourseBasicAuthConfiguration.class)
public interface AuthClient {
	
	   @RequestLine("GET /api/v1/teams/zero/auth/token")
	   ConcourseAPIAccessToken login();
}
