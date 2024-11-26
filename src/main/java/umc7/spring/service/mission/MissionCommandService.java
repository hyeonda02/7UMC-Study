package umc7.spring.service.mission;

import umc7.spring.domain.Mission;
import umc7.spring.domain.mappings.MissionComplete;
import umc7.spring.dto.MissionReqDto;

public interface MissionCommandService {
    Mission createMission(MissionReqDto request, Long storeId, Long memberId);

    MissionComplete addChallenge(Long missionId, Long memberId);
}
