package com.everyTing.core.config;

import com.everyTing.core.cors.CorsFilter;
import com.everyTing.core.token.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    private final TokenFilter tokenFilter;
    private final CorsFilter corsFilter;

    public FilterConfig(TokenFilter tokenFilter, CorsFilter corsFilter) {
        this.tokenFilter = tokenFilter;
        this.corsFilter = corsFilter;
    }

    @Bean
    public FilterRegistrationBean<Filter> addTokenFilter() {
        var filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(tokenFilter);
        filterRegistrationBean.addUrlPatterns(TokenFilter.APPLY_URL_PATTERNS);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> addCorsFilter() {
        var filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(corsFilter);
        filterRegistrationBean.addUrlPatterns(CorsFilter.APPLY_URL_PATTERNS);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistrationBean;
    }
}

