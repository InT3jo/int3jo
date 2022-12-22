package project3.yakdo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.Filter;
import project3.yakdo.filter.LoginFilter;

/**
 * 필요한 설정 주입하기 위한 클래스
 * @author honey
 *
 */
@Configuration
public class FilterConfig {

	
	/**
	 * 로그인 관련 필터
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<Filter> loginFilter() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		
		filterRegistrationBean.setFilter(new LoginFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.addUrlPatterns("/*");
		
		return filterRegistrationBean;
	}
}
