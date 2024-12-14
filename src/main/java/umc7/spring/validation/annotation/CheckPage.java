package umc7.spring.validation.annotation;

import io.swagger.v3.oas.annotations.Parameter;

import java.lang.annotation.*;

@Documented
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(hidden = true)
public @interface CheckPage {

}
