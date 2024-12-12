package umc7.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.Review;
import umc7.spring.domain.Store;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewRepositoryCustom {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

}
