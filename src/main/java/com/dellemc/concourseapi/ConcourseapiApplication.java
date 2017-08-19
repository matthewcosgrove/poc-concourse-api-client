package com.dellemc.concourseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import feign.Contract;

@SpringBootApplication
@EnableFeignClients
public class ConcourseapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcourseapiApplication.class, args);
	}

	@Bean
	public Contract useFeignAnnotations() {
		// https://stackoverflow.com/questions/29985205/using-requestline-with-feign
		return new Contract.Default();
	}


}
