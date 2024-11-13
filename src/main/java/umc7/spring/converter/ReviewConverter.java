package umc7.spring.converter;

import umc7.spring.domain.Member;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;
import umc7.spring.dto.ReviewDto;

public class ReviewConverter {
    public static Review toReview(Member requestMember, ReviewDto dto, Store store){
        return Review.builder()
                .content(dto.getContent())
                .score(dto.getScore())
                .member(requestMember)
                .store(store)
                .build();

    }
}
