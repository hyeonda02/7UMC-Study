package umc7.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import umc7.spring.validation.validator.PageCheckArgumentResolver;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private PageCheckArgumentResolver pageCheckArgumentResolver;
    public WebConfig(PageCheckArgumentResolver pageCheckArgumentResolver) {
        this.pageCheckArgumentResolver = pageCheckArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(pageCheckArgumentResolver);
    }
}
