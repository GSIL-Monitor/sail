package com.study.spring.sail.config.web;

import com.study.spring.sail.config.web.exception.ExitException;
import com.study.spring.sail.config.web.filter.MyFilter;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * spring mvc 配置
 *
 * @author 韩炜
 * @date 2019-01-24 15:40
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 跨域请求设置
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowedHeaders("x-requested-with")
                .allowCredentials(true)
                .maxAge(3600);

    }

    /**
     * 自定义filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    /**
     * 启动遇到问题关闭服务返回状态码
     *
     * @return
     */
    @Bean
    ExitCodeExceptionMapper exitCodeToexceptionMapper() {
        return exception -> {
            // set exit code base on the exception type
            if (exception.getCause() instanceof ExitException) {
                return 80;
            }
            return 1;
        };
    }

    /**
     * 语言环境设置
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }

    /**
     * 根据lang 切换语言环境
     *
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
