package com.dellemc.concourseapi;

import org.springframework.cloud.netflix.feign.FeignClient;

import config.feign.ConcourseBasicAuthConfiguration;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "AuthClient", url = "${concourse.host.url}", configuration = ConcourseBasicAuthConfiguration.class)
public interface AuthClient {
	
	   @RequestLine("GET /api/v1/teams/{concourseTeam}/auth/token")
	   ConcourseAPIAccessToken login(@Param("concourseTeam") String concourseTeam);
}
