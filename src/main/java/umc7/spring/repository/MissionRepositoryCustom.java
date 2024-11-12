package umc7.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc7.spring.domain.Mission;

import java.util.List;

public interface MissionRepositoryCustom {


    Page<Mission> findByMemberWithCompleteMission(Long memberId, Boolean status, Pageable pageable);
}
