package com.dellemc.concourseapi;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.dellemc.concourseapi.response.ConcourseAPIBuildResult;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "ConcourseAPIClient", url = "${concourse.host.url}")
public interface ConcourseAPIClient {
	
	   @RequestLine("GET /api/v1/builds/{globalBuildIdentifier}")
	   @Headers({"Cookie: ATC-Authorization=Bearer {accessToken}"})
	   String getRawBuildResult(@Param("accessToken") String accessToken, @Param("globalBuildIdentifier") int globalBuildIdentifier);

	   @RequestLine("GET /api/v1/builds/{globalBuildIdentifier}")
	   @Headers({"Cookie: ATC-Authorization=Bearer {accessToken}"})
	   ConcourseAPIBuildResult getBuildResult(@Param("accessToken") String accessToken, @Param("globalBuildIdentifier") int globalBuildIdentifier);

}
