package umc7.spring.converter;

import org.springframework.data.domain.Page;
import umc7.spring.controller.response.MissionListResponse;
import umc7.spring.controller.response.MissionResponse;
import umc7.spring.domain.Mission;
import umc7.spring.domain.Store;
import umc7.spring.dto.MissionReqDto;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static Mission toMission(MissionReqDto request, Store store){
        return Mission.builder()
                .content(request.getContent())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .store(store).build();

    }
    public static MissionListResponse toMissionListResponse(Page<Mission> mission){
        List<MissionResponse> missionList = mission.stream()
                .map(MissionResponse::new).collect(Collectors.toList());

        return MissionListResponse.builder()
                .isLast(mission.isLast())
                .isFirst(mission.isFirst())
                .totalElements(mission.getTotalElements())
                .totlaPage(mission.getTotalPages())
                .listSize(mission.getSize())
                .missionList(missionList)
                .build();
    }
}
