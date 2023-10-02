package com.everyTing.core.config;

import com.everyTing.core.token.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    private final TokenFilter tokenFilter;

    public FilterConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public FilterRegistrationBean<Filter> addTokenFilter() {
        var filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(tokenFilter);
        filterRegistrationBean.addUrlPatterns(TokenFilter.APPLY_URL_PATTERNS);
        return filterRegistrationBean;
    }
}

