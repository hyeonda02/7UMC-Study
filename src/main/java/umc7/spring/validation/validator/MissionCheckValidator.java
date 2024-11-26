package umc7.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc7.spring.apiPayload.code.status.ErrorStatus;
import umc7.spring.service.mission.MissionQueryService;
import umc7.spring.validation.annotation.CheckMission;

@Component
@RequiredArgsConstructor
public class MissionCheckValidator implements ConstraintValidator<CheckMission, Long> {
    private final MissionQueryService missionQueryService;

    @Override
    public void initialize(CheckMission constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        Long memberId = 1L;
        if (missionId == null) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.getMessage()).addConstraintViolation();

        }
        boolean isValid = missionQueryService.checkMissionChallenge(memberId, missionId);
        if(!isValid){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_IS_CHALLENGING.toString()).addConstraintViolation();
        }
        return isValid;
    }
}
