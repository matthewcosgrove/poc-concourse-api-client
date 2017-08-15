package com.dellemc.concourseapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import feign.Contract;

@SpringBootApplication
@EnableFeignClients
public class ConcourseapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcourseapiApplication.class, args);
	}

	@Bean
	@Autowired
	public Contract useFeignAnnotations(MappingJackson2HttpMessageConverter converter) {
		List<MediaType> types = Arrays.asList(MediaType.APPLICATION_JSON, new MediaType("application", "*+json"), MediaType.TEXT_PLAIN);
		converter.setSupportedMediaTypes(types);
		// https://stackoverflow.com/questions/29985205/using-requestline-with-feign
		return new Contract.Default();
	}


}
