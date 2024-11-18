package umc7.spring.service;

import umc7.spring.domain.Mission;

import java.util.List;
import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long id);
    List<Mission> findMissionsByMemberIdAndStatus(Long memberId, Boolean status);
    List<Mission> findMissionsByMemberIdAndRegion(Long memerId, Long regionId);
}
