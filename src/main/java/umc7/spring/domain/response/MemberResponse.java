package umc7.spring.domain.response;

import lombok.*;
import umc7.spring.domain.Member;
import umc7.spring.domain.enums.Gender;
import umc7.spring.domain.enums.MemberStatus;
import umc7.spring.domain.mappings.MemberPrefer;

import java.util.List;


@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String address;
    private String name;
    private String phone;
    private String email;
    private Integer totalPoint;
    private MemberStatus status;
    private Gender gender;
    private List<MemberPrefer> categoryList;
    public MemberResponse(Member member) {
        this.id = member.getId();
        this.address = member.getAddress();
        this.name = member.getName();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.totalPoint = member.getTotalPoint();
        this.status = member.getStatus();
        this.gender = member.getGender();
        this.categoryList = member.getMemberPreferList();

    }
}
