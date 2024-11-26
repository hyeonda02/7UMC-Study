package umc7.spring.converter;

import umc7.spring.domain.Member;
import umc7.spring.domain.Mission;
import umc7.spring.domain.mappings.MissionComplete;

public class MissionCompleteConverter {
    public static MissionComplete toMissionComplete(Member member, Mission mission) {
        return MissionComplete.builder()
                .member(member)
                .mission(mission)
                .status(false)
                .build();
    }
}
