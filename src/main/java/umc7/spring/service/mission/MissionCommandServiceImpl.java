package umc7.spring.service.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.apiPayload.code.status.ErrorStatus;
import umc7.spring.apiPayload.exception.handler.MemberHandler;
import umc7.spring.converter.MissionConverter;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;
import umc7.spring.dto.MissionReqDto;
import umc7.spring.repository.MissionRepository;
import umc7.spring.repository.StoreRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    @Override
    @Transactional
    public Mission createMission(MissionReqDto request, Long storeId, Long memberId) {
        Store store = storeRepository.findById(storeId).get();
        Mission newMission = MissionConverter.toMission(request, store);
        store.getMissionList().add(newMission);
        return missionRepository.save(newMission);
    }
}
