package umc7.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.domain.Mission;
import umc7.spring.domain.mappings.MissionComplete;
import umc7.spring.repository.MissionCompleteRepository;
import umc7.spring.repository.MissionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;
    private final MissionCompleteRepository missionCompleteRepository;
    @Override
    public Optional<Mission> findMission(Long id) {
        return missionRepository.findById(id);
    }
    @Override
    public Page<Mission> findMissionsByMemberIdAndStatus(Long memberId, Boolean status, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        return missionRepository.findByMemberWithCompleteMission(memberId,status,pageable);
    }

    @Override
    public List<Mission> findMissionsByMemberIdAndRegion(Long memerId, Long regionId) {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<Mission> filteredMission = missionRepository.findByMemberWithRegion(memerId,regionId,pageable);
        filteredMission.forEach(mission -> System.out.println("Mission : " + mission));
        return filteredMission.getContent();
    }
    @Override
    public boolean checkMissionChallenge(Long memberId, Long missionId) {
        Optional<MissionComplete> missionStatus = missionCompleteRepository.findByMemberIdAndMissionId(memberId, missionId);
        if(missionStatus.isPresent()&&!missionStatus.get().getStatus()){
            return false;
        }else return true;
    }

}
