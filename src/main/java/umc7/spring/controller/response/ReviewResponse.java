package umc7.spring.controller.response;

import lombok.*;
import umc7.spring.domain.Review;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private Long storeId;
    private String content;
    private Float score;
    private String reviewOwner;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.storeId = review.getStore().getId();
        this.content = review.getContent();
        this.score = review.getScore();
        this.reviewOwner = review.getMember().getName();
    }
}
