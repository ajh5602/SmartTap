package API_SERVER;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

public class SecurityConfig {
	public FilterRegistrationBean oauth2ClientFilterRegistration(Filter filter) {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}

	public FilterRegistrationBean auth2ClientFilterRegistration(Filter filter) {
		FilterRegistrationBean<Filter> registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		return registration;
		// express 의존 모듈 구성

	}
}