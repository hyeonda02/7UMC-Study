package umc7.spring.converter;

import org.springframework.data.domain.Page;
import umc7.spring.controller.response.ReviewListResponse;
import umc7.spring.controller.response.ReviewResponse;
import umc7.spring.domain.Member;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;
import umc7.spring.dto.ReviewReqDto;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static Review toReview(Member requestMember, ReviewReqDto request, Store store){
        return Review.builder()
                .content(request.getContent())
                .score(request.getScore())
                .member(requestMember)
                .store(store)
                .build();
    }

    public static ReviewListResponse tpReviewListResponse(Page<Review> reviewList) {
        List<ReviewResponse> reviewResponse = reviewList.stream()
                .map(ReviewResponse::new).collect(Collectors.toList());

        return ReviewListResponse.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalElements(reviewList.getTotalElements())
                .totlaPage(reviewList.getTotalPages())
                .listSize(reviewList.getSize())
                .reviewList(reviewResponse)
                .build();
    }
}
