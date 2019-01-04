package com.project.tour.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.support.MultipartFilter;

import java.io.IOException;

@Configuration
public class MultipartConfig
{
//    @Bean
//    public CommonsMultipartResolver commonsMultipartResolver() throws IOException
//    {
//        final CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//        commonsMultipartResolver.setMaxUploadSize(-1);
//        commonsMultipartResolver.setDefaultEncoding("utf-8");
//        return commonsMultipartResolver;
//    }

    /**
     * 파일 업로드를 위한 빈
     *
     * @return
     * @throws IOException
     */
    @Bean(name = "commonsMultipartResolver")
    public PutMultipartResolver putMultipartResolver() throws IOException
    {
        final PutMultipartResolver putMultipartResolver = new PutMultipartResolver();
        putMultipartResolver.setMaxUploadSize(-1);
        putMultipartResolver.setDefaultEncoding("utf-8");
        return putMultipartResolver;
    }

    @Bean
    public FilterRegistrationBean multipartFilterRegistrationBean()
    {
        final MultipartFilter multipartFilter = new MultipartFilter();
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(multipartFilter);
        filterRegistrationBean.addInitParameter("multipartResolverBeanName", "commonsMultipartResolver");
        return filterRegistrationBean;
    }
}
