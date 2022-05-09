package com.debamalya.routine;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CorsConfig{
	
	@Bean
	public FilterRegistrationBean corsFilterRegistration() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CorsFilter());
		filterRegistrationBean.setName("Cors Filter");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}
	

}
