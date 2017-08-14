package com.dellemc.concourseapi;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcourseapiApplicationTests {

	@Autowired
	AuthClient authClient;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void canCallConcourseAPIForToken() throws Exception {
//		String accessToken = authClient.login();
		ConcourseAPIAccessToken accessToken = authClient.login();
		assertNotNull(accessToken);
		System.err.println(accessToken);
//		assertNotNull(accessToken.getAccessToken());
//		System.out.println(accessToken.getAccessToken());
	}

}
