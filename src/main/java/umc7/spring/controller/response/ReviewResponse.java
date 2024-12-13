package umc7.spring.controller.response;

import lombok.*;
import umc7.spring.domain.Review;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ReviewResponse {
    private Long id;
    private String content;
    private Float score;
    private String reviewOwner;
    private LocalDateTime createdAt;

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.score = review.getScore();
        this.reviewOwner = review.getMember().getName();
        this.createdAt = review.getCreatedAt();
    }
}
