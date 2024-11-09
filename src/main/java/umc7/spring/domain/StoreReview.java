package umc7.spring.domain;
import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_review_id")
    private Long id;
    private String content;

}
