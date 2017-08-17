package com.dellemc.concourseapi.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConcourseAPIBuildResult {

	private int globalBuildIdentifier;
	
	private int pipelineSpecificBuildNumber;

	@JsonProperty("id")
	public int getGlobalBuildIdentifier() {
		return globalBuildIdentifier;
	}

	@JsonProperty("name")
	public int getPipelineSpecificBuildNumber() {
		return pipelineSpecificBuildNumber;
	}
}
