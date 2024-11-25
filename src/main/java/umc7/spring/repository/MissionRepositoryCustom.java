package umc7.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc7.spring.domain.Mission;

public interface MissionRepositoryCustom {

    Page<Mission> findByMemberWithCompleteMission(Long memberId, Boolean status, Pageable pageable);
    Page<Mission> findByMemberWithRegion(Long memberId, Long regionId ,Pageable pageable);

}
