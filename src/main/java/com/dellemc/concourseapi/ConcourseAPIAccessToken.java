package com.dellemc.concourseapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConcourseAPIAccessToken {

	private String accessToken;

	@JsonProperty("value")
	public String getAccessToken() {
		return accessToken;
	}
}
