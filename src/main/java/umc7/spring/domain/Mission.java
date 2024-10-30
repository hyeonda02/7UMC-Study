package umc7.spring.domain;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import umc7.spring.domain.common.BaseEntity;
import umc7.spring.domain.mappings.MemberPrefer;
import umc7.spring.domain.mappings.MissionComplete;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mission_id")
    private Long id;
    private Integer reward;
    private LocalDate deadline;

    @Column(nullable = false, length = 50)
    private String content;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MissionComplete> missionCompleteList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;
}
