package umc7.spring.service.review;

import org.springframework.data.domain.Page;
import umc7.spring.domain.Review;

public interface ReviewQueryService {
    Page<Review> getReviewList(Long storeId, Integer page);
    Page<Review> getReviewListByMemberId(Long memberId, Integer page);
}
