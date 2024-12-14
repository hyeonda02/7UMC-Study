package umc7.spring.validation.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc7.spring.validation.annotation.CheckPage;

@Slf4j
@Component
public class PageCheckArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isSupported =  parameter.hasParameterAnnotation(CheckPage.class);
        log.info("호출됨"+isSupported);
        return isSupported;
    }

    @Override
    public Integer resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument 호출됨");
        String pageParam = webRequest.getParameter("page");
        if(pageParam==null||!pageParam.matches("\\d+")){
            log.warn("올바르지 않은 값입니다. :"+pageParam);
            return 0;
        }
        int page = Integer.parseInt(pageParam);
        log.info("로그 : {}", page-1 );
        return page -1;
    }
}
