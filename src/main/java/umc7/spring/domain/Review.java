package umc7.spring.domain;
import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;

@Builder
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @Column(name="review_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Float score;

}
