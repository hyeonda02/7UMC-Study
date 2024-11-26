package umc7.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc7.spring.validation.validator.MissionCheckValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionCheckValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckMission {
    String message() default "해당 미션은 이미 도전 중입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
