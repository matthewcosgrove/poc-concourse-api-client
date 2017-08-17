package com.dellemc.concourseapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dellemc.concourseapi.response.ConcourseAPIBuildResult;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcourseapiApplicationTests {

	@Autowired
	AuthClient authClient;

	@Autowired
	ConcourseAPIClient concourseAPIClient;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void canCallConcourseAPIForToken() throws Exception {
		ConcourseAPIAccessToken accessToken = authClient.login();
		assertNotNull(accessToken);
		assertNotNull(accessToken.getAccessToken());
		System.err.println(accessToken.getAccessToken());
	}

	@Test
	public void canCallConcourseAPIForBuildInfo() throws Exception {
		ConcourseAPIAccessToken accessToken = authClient.login();
		int buildNumber = 38;
		int globalBuildIdentifier = 52;
		String resp = concourseAPIClient.getRawBuildResult(accessToken.getAccessToken(), globalBuildIdentifier);
		assertNotNull(resp);
		System.err.println(resp);

		ConcourseAPIBuildResult buildResult = concourseAPIClient.getBuildResult(accessToken.getAccessToken(), globalBuildIdentifier);
		int pipelineSpecificBuildNumber = buildResult.getPipelineSpecificBuildNumber();
		assertNotNull(resp);
		assertEquals(buildNumber, pipelineSpecificBuildNumber);
		System.err.println(pipelineSpecificBuildNumber);
	}

}
