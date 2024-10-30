package umc7.spring.domain;
import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Question extends BaseEntity{
    @Id
    @Column(name="question_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String content;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
