package com.dellemc.concourseapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("${concourse.team}")
	String concourseTeam;

	// int buildNumber = 38; // left here to show there is a difference between build number and it's identifier (which below we call globalBuildIdentifier) e.g. this test was written against build 38 with identifier 52
	int globalBuildIdentifier = 52;

	@Test
	public void contextLoads() {
	}

	@Test
	public void canCallConcourseAPIForToken() throws Exception {
		ConcourseAPIAccessToken accessToken = authClient.login(concourseTeam);
		assertNotNull(accessToken);
		assertNotNull(accessToken.getAccessToken());
		System.err.println(accessToken.getAccessToken());
	}

	@Test
	public void canCallConcourseAPIForBuildInfo() throws Exception {
		ConcourseAPIAccessToken accessToken = authClient.login(concourseTeam);
		String resp = concourseAPIClient.getRawBuildResult(accessToken.getAccessToken(), globalBuildIdentifier);
		assertNotNull(resp);
		System.err.println(resp);

		ConcourseAPIBuildResult buildResult = concourseAPIClient.getBuildResult(accessToken.getAccessToken(),
				globalBuildIdentifier);
		int pipelineSpecificBuildNumber = buildResult.getPipelineSpecificBuildNumber();
		assertNotNull(resp);
		assertEquals(buildNumber, pipelineSpecificBuildNumber);
		System.err.println(pipelineSpecificBuildNumber);
	}

	@Autowired
	ServerSentEventsJaxRSClient eventStreamClient;

	@Test
	public void canCallConcourseAPIForEventStreamOfCompletedBuild() throws Exception {
		ConcourseAPIAccessToken login = authClient.login(concourseTeam);
		String accessToken = login.getAccessToken();

		eventStreamClient.eventStreamWithToken(accessToken, globalBuildIdentifier);
		Thread.sleep(5000);
	}

}
