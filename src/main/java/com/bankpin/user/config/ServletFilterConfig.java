package com.bankpin.user.config;

import com.bankpin.user.ext.coocon.config.filter.CooconLoggingFilter;
import com.bankpin.user.ext.coocon.service.CooconLogService;
import com.bankpin.user.ext.kcb.config.filter.KcbLoggingFilter;
import com.bankpin.user.ext.kcb.service.KcbSmsLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletFilterConfig {

    @Autowired
    private CooconLogService cooconLogService;
    @Autowired
    private KcbSmsLogService kcbSmsLogService;

    @Bean
    public FilterRegistrationBean<CooconLoggingFilter> CooconLoggingFilter() {
        FilterRegistrationBean<CooconLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CooconLoggingFilter(cooconLogService));
        registrationBean.addUrlPatterns("/api/v1/coocon/*", "/coocon/callback/*");
        registrationBean.setName("coocon-logging-filter");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<KcbLoggingFilter> KcbLoggingFilter() {
        FilterRegistrationBean<KcbLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new KcbLoggingFilter(kcbSmsLogService));
        registrationBean.addUrlPatterns("/api/v1/kcb/sms/*");
        registrationBean.setName("kcb-logging-filter");
        return registrationBean;
    }

}
