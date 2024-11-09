package umc7.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;
import umc7.spring.domain.enums.Gender;
import umc7.spring.domain.enums.MemberStatus;
import umc7.spring.domain.mappings.MemberPrefer;
import umc7.spring.domain.mappings.MissionComplete;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false, length = 50)
    private String email;

    private LocalDate inactiveDate;

    //디폴트 0으로 하기
    private Integer totalPoint;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MissionComplete> missionCompleteList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PointTransaction> pointTransactionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();
}
