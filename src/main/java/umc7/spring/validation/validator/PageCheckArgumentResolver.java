package umc7.spring.validation.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc7.spring.apiPayload.code.status.ErrorStatus;
import umc7.spring.apiPayload.exception.handler.PageHandler;
import umc7.spring.validation.annotation.CheckPage;

@Slf4j
@Component
public class PageCheckArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isSupported =  parameter.hasParameterAnnotation(CheckPage.class);
        log.info("PageCheckArgumentResolver 호출됨"+isSupported);
        return isSupported;
    }

    @Override
    public Integer resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        CheckPage annotation = parameter.getParameterAnnotation(CheckPage.class);
        String paramName = annotation.name();
        int defaultValue = annotation.defaultValue();
        String paramValue = webRequest.getParameter(paramName);

        if (paramValue == null) {
            return defaultValue;
        }
        if (!isValidPage(paramValue)) {
            new PageHandler(ErrorStatus.PAGE_BAD_REQUEST);
        }

        int page = Integer.parseInt(paramValue);
        return page - 1;
    }

    private boolean isValidPage(String paramValue) {
        if (!paramValue.matches("\\d+")) {
            return false;
        }
        int page = Integer.parseInt(paramValue);
        return page <= 0;

    }
}
