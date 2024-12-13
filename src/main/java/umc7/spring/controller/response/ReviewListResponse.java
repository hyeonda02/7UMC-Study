package umc7.spring.controller.response;

import lombok.*;
import umc7.spring.domain.Review;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewListResponse {
    private List<ReviewResponse> reviewList;
    private int listSize;
    private int totlaPage;
    private Long totalElements;
    private Boolean isFirst;
    private Boolean isLast;

}
