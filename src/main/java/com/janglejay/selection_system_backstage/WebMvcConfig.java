package com.janglejay.selection_system_backstage;

import com.janglejay.selection_system_backstage.interceptor.LoginInterceptor;
import com.janglejay.selection_system_backstage.interceptor.TutorInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private TutorInterceptor tutorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {//注入拦截器对象
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");

        registry.addInterceptor(tutorInterceptor)
                .addPathPatterns("/api/tutor/**");
    }
}
