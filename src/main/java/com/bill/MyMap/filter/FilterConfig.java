package com.bill.MyMap.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FilterConfig {
	
	@Autowired
	private RequestParamsManagerFilter requestParamsManagerFilter;
	
	@Bean(name = "RequestParamsManagerFilterBean")
	public FilterRegistrationBean RequestParamsManagerFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setFilter(requestParamsManagerFilter);
		filterRegistrationBean.setName("RequestParamsManagerFilter");
		filterRegistrationBean.setOrder(Integer.MAX_VALUE);
		return filterRegistrationBean;
	}
}
