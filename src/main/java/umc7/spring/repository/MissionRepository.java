package umc7.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;

public interface MissionRepository extends JpaRepository<Mission,Long>, MissionRepositoryCustom {
    Page<Mission> findAllByStore(Store store, PageRequest of);
}
