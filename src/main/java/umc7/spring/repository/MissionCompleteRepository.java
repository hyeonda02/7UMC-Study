package umc7.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.mappings.MissionComplete;

import java.util.Optional;

public interface MissionCompleteRepository extends JpaRepository<MissionComplete,Long> {
    Optional<MissionComplete> findByMemberIdAndMissionId(Long memberId, Long missionId);
}
