package umc7.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.apiPayload.code.status.ErrorStatus;
import umc7.spring.apiPayload.exception.handler.MemberHandler;
import umc7.spring.apiPayload.exception.handler.MissionHandler;
import umc7.spring.converter.MissionCompleteConverter;
import umc7.spring.converter.MissionConverter;
import umc7.spring.domain.Member;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;
import umc7.spring.domain.mappings.MissionComplete;
import umc7.spring.dto.MissionReqDto;
import umc7.spring.repository.MemberRepository;
import umc7.spring.repository.MissionCompleteRepository;
import umc7.spring.repository.MissionRepository;
import umc7.spring.repository.StoreRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MissionCompleteRepository missionCompleteRepository;
    @Override
    @Transactional
    public Mission createMission(MissionReqDto request, Long storeId, Long memberId) {
        Store store = storeRepository.findById(storeId).get();
        Mission newMission = MissionConverter.toMission(request, store);
        store.getMissionList().add(newMission);
        return missionRepository.save(newMission);
    }

    @Override
    @Transactional
    public MissionComplete addChallenge(Long missionId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(()-> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        return missionCompleteRepository.save(MissionCompleteConverter.toMissionComplete(member, mission));
    }
}
