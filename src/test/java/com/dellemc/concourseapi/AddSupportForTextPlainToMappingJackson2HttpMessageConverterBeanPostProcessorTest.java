package com.dellemc.concourseapi;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class AddSupportForTextPlainToMappingJackson2HttpMessageConverterBeanPostProcessorTest {
	
	AddSupportForTextPlainToMappingJackson2HttpMessageConverterBeanPostProcessor beanPostProcessor = new AddSupportForTextPlainToMappingJackson2HttpMessageConverterBeanPostProcessor();

	@Test
	public void allClassesPassThroughWithoutExceptionBeforeInitialization() {
		assertTrue(RestTemplate.class.isAssignableFrom(beanPostProcessor.postProcessBeforeInitialization(new RestTemplate(), "restTemplate").getClass()));
		Object converterWhichShouldNotBeReConfiguredBeforeInit = beanPostProcessor.postProcessBeforeInitialization(new MappingJackson2HttpMessageConverter(), "converterWhichShouldNotBeReConfiguredBeforeInit");
		assertTrue(MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterWhichShouldNotBeReConfiguredBeforeInit.getClass()));
		MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) converterWhichShouldNotBeReConfiguredBeforeInit;
		assertFalse(converter.getSupportedMediaTypes().contains(MediaType.TEXT_PLAIN));
	}
	
	@Test
	public void otherClassesPassThroughWithoutExceptionAfterInitialization() {
		assertTrue(RestTemplate.class.isAssignableFrom(beanPostProcessor.postProcessAfterInitialization(new RestTemplate(), "restTemplate").getClass()));
	}
	
	@Test
	public void addsSupportForTextPlainToMappingJackson2HttpMessageConverterAfterInitialization() {
		Object converterWhichShouldBeReConfiguredAfterInit = beanPostProcessor.postProcessAfterInitialization(new MappingJackson2HttpMessageConverter(), "converterWhichShouldBeConfiguredAfterInit");
		assertTrue(MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterWhichShouldBeReConfiguredAfterInit.getClass()));
		MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) converterWhichShouldBeReConfiguredAfterInit;
		assertTrue(converter.getSupportedMediaTypes().contains(MediaType.TEXT_PLAIN));
	}

}
