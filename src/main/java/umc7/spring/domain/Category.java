package umc7.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc7.spring.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Category extends BaseEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name="food_category_id")
    private Long id;
    @Column(nullable = false, length = 15)
    private String name;
}
