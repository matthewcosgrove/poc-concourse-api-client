package com.dellemc.concourseapi;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.dellemc.concourseapi.response.ConcourseAPIBuildResult;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

@FeignClient(name = "ConcourseAPIClient", url = "${concourse.host.url}")
public interface ConcourseAPIClient {
	
	   @RequestLine("GET /api/v1/teams/zero/pipelines/zero-pivnet-to-s3/jobs/upload-tile-to-s3/builds/{buildNumber}")
	   @Headers({"Cookie: ATC-Authorization=Bearer {accessToken}"})
	   String getBuildInfoForSpecificPipeline(@Param("accessToken") String accessToken, @Param("buildNumber") int buildNumber);

	   @RequestLine("GET /api/v1/teams/zero/pipelines/zero-pivnet-to-s3/jobs/upload-tile-to-s3/builds/{buildNumber}")
	   @Headers({"Cookie: ATC-Authorization=Bearer {accessToken}"})
	   ConcourseAPIBuildResult getBuildResultForSpecificPipeline(@Param("accessToken") String accessToken, @Param("buildNumber") int buildNumber);
}
