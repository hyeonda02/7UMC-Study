package umc7.spring.domain;
import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;
import umc7.spring.domain.mappings.MissionComplete;

import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="store_id")
    private Long id;
    @Column(nullable = false, length=50)
    private String name;
    @Column(nullable=false, length = 50)
    private String address;
    private Float score;
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
