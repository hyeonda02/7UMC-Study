package umc7.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.converter.ReviewConverter;
import umc7.spring.domain.Member;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;
import umc7.spring.dto.ReviewDto;
import umc7.spring.repository.ReviewRepository;
import umc7.spring.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public Review createReview(Member requestMember, ReviewDto reviewDto, Long storeId){
        Store store = storeRepository.findById(storeId).get();
        Review review = ReviewConverter.toReview(requestMember, reviewDto, store);
        return reviewRepository.save(review);

    }
}
