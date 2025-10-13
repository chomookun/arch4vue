package org.chomookun.vuejsexample;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(
                        "file:src/main/vuejs/dist/"
                )
                .setCachePeriod(0); // 개발 중 캐싱 방지
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 확장자 없는 경로만 index.html로 포워딩
        registry.addViewController("/{spring:[a-zA-Z0-9-_]+}")
                .setViewName("forward:/index.html");

        registry.addViewController("/**/{spring:[a-zA-Z0-9-_]+}")
                .setViewName("forward:/index.html");

        registry.addViewController("/{spring:[a-zA-Z0-9-_]+}/**{spring:[a-zA-Z0-9-_]+}")
                .setViewName("forward:/index.html");

    }

}
