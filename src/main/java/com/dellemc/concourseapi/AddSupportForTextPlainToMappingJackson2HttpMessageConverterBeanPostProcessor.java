package com.dellemc.concourseapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public class AddSupportForTextPlainToMappingJackson2HttpMessageConverterBeanPostProcessor implements BeanPostProcessor {

	private Class<MappingJackson2HttpMessageConverter> converterClass = MappingJackson2HttpMessageConverter.class;

	@Override
	public Object postProcessAfterInitialization(Object bean, String name) throws BeansException {
		if (converterClass.isAssignableFrom(bean.getClass())) {
			MappingJackson2HttpMessageConverter converter = (MappingJackson2HttpMessageConverter) bean;
			List<MediaType> types = Arrays.asList(MediaType.APPLICATION_JSON, new MediaType("application", "*+json"),
					MediaType.TEXT_PLAIN);
			converter.setSupportedMediaTypes(types);
		}
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String name) throws BeansException {
		return bean;
	}

}
