package com.dellemc.concourseapi;

import javax.annotation.PreDestroy;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerSentEventsJaxRSClient {

	Client sseClient;
	WebTarget target;
	
	@Value("${concourse.host.url}")
	String hostUrl;
	
	SseEventSource eventSource;
	
	private void setUpEventSource(String accessToken, int globalBuildIdentifier) {
		this.sseClient = ClientBuilder.newBuilder()
		                             .register(new AuthTokenCookieHeaderFeature(accessToken))
		                             .build();
		WebTarget target = this.sseClient.target(hostUrl + "/api/v1/builds/" + globalBuildIdentifier + "/events");
		eventSource = SseEventSource.target(target).build();
	}

	public void eventStreamWithToken(String accessToken, int globalBuildIdentifier) {
		setUpEventSource(accessToken, globalBuildIdentifier);
		eventStream();
	}

	private void eventStream() {
		System.out.println("SSE Client triggered in thread " + Thread.currentThread().getName());

		try {
			eventSource.register((sseEvent) -> {
				System.out.println("Events received in thread " + Thread.currentThread().getName());
				System.out.println("SSE event recieved ----- " + sseEvent.readData());
			}, (e) -> System.err.println(e));

			eventSource.open();
			System.out.println("Source open ????? " + eventSource.isOpen());
			if(!eventSource.isOpen()) {
				throw new RuntimeException("Cannot open connection. Have you authenticated correctly?");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw e;
		}

	}

	@PreDestroy
	public void close() {
		eventSource.close();
		System.out.println("Closed SSE Event source..");
		sseClient.close();
		System.out.println("Closed JAX-RS client..");
	}
}
