package umc7.spring.controller.response;

import lombok.*;
import umc7.spring.domain.Mission;

import java.time.LocalDate;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MissionResponse {
    private Long id;
    private String content;
    private String storeName;
    private Integer reword;
    private LocalDate deadline;

    public MissionResponse(Mission mission) {
        this.id = mission.getId();
        this.storeName = mission.getStore().getName();
        this.content = mission.getContent();
        this.reword = mission.getReward();
        this.deadline = mission.getDeadline();
    }
}
