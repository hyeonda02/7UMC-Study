package umc7.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.mappings.MissionComplete;

public interface MissionCompleteRepository extends JpaRepository<MissionComplete,Long> {
}
