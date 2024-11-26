package umc7.spring.controller.response;

import lombok.*;
import umc7.spring.converter.MissionCompleteConverter;
import umc7.spring.domain.mappings.MissionComplete;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MissionCompleteResponse {
    private Long id;
    private Long memberId;
    private  Long missionId;
    private Boolean status;
    private String missionContent;
    private Integer missionReward;

    public MissionCompleteResponse(MissionComplete missionComplete) {
        this.id = missionComplete.getId();
        this.memberId = missionComplete.getMember().getId();
        this.missionId = missionComplete.getMission().getId();
        this.missionContent = missionComplete.getMission().getContent();
        this.missionReward = missionComplete.getMission().getReward();
        this.status = missionComplete.getStatus();
    }

}
