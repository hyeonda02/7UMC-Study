package umc7.spring.service.mission;

import org.springframework.data.domain.Page;
import umc7.spring.domain.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long id);
    Page<Mission> findMissionsByMemberIdAndStatus(Long memberId, Boolean status, int page);
    List<Mission> findMissionsByMemberIdAndRegion(Long memerId, Long regionId);
    boolean checkMissionChallenge(Long memberId, Long missionId);
}
