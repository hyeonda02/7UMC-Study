package umc7.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission,Long>, MissionRepositoryCustom {
}
