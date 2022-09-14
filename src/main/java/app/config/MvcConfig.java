package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/complexsearch").setViewName("complexsearch");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/manager").setViewName("manager");
        registry.addViewController("/passenger").setViewName("passenger");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        /*registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");*/
    }
}
