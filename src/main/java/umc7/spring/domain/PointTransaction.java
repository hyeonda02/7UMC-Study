package umc7.spring.domain;
import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;
import umc7.spring.domain.enums.TransactionType;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PointTransaction extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="point_transaction_id")
    private Long id;
    private String content;
    private int point;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


}
