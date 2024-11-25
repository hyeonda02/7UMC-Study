package umc7.spring.service;

import umc7.spring.domain.Review;
import umc7.spring.dto.ReviewReqDto;

public interface ReviewCommandService {
    Review createReview(ReviewReqDto request, Long storeId, Long memberId);
}
