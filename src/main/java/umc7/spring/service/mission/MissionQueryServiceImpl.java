package umc7.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.domain.Mission;
import umc7.spring.repository.MissionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }
    @Override
    public List<Mission> findMissionsByMemberIdAndStatus(Long memberId, Boolean status) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<Mission> filterdMission = missionRepository.findByMemberWithCompleteMission(memberId,status,pageable);
        filterdMission.forEach(mission -> System.out.println("Mission : "+mission));
        return filterdMission.getContent();
    }

    @Override
    public List<Mission> findMissionsByMemberIdAndRegion(Long memerId, Long regionId) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<Mission> filteredMission = missionRepository.findByMemberWithRegion(memerId,regionId,pageable);
        filteredMission.forEach(mission -> System.out.println("Mission : " + mission));
        return filteredMission.getContent();
    }
}
