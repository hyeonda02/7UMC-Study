package umc7.spring.controller.response;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MissionListResponse {
    private List<MissionResponse> missionList;
    private int listSize;
    private int totlaPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;

}
