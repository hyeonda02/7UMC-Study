package umc7.spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc7.spring.validation.annotation.ExistCategories;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberReqDto {

    @NotBlank(message = "이름은 필수 입력 항목입니다. 최대 10자 까지 입력 가능")
    @Size(max = 10)
    @Schema(description = "이름", example = "강다현", type="string")
    private String name;

    @NotBlank(message = "주소는 필수 입력 항목입니다. 최대 30자 까지 입력 가능")
    @Size(max = 20)
    @Schema(description = "주소", example = "경기 파주시 책향기로", type="string")
    private String address;

    @NotBlank(message = "이메일은 필수 입력 항목입니다. 최대 40자 까지 입력 가능")
    @Size(max = 40)
    @Schema(description = "이메일", example = "dhk02@hansung.ac.kr", type="string")
    private String email;

    @NotNull(message = "성별은 필수 입력 항목입니다. 남성일 경우 1, 여성일 경우 2 값을 넘겨주세요.")
    @Schema(description = "성별", example = "1", type = "int")
    private Integer gender;

    @NotBlank(message = "전화번호는 필수 입력 항목입니다. 최대 15자 까지 입력 가능")
    @Size(max = 15)
    @Schema(description = "전화번호", example = "010-1234-1234", type="string")
    private String phone;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다. 최대 15자 까지 입력 가능")
    @Size(max = 15)
    @Schema(description = "비밀번호", example = "password123", type="string")
    private String password;

    @NotBlank(message = "비밀번호는 필수 입력 항목입니다. 최대 15자 까지 입력 가능")
    @Size(max = 15)
    @Schema(description = "비밀번호", example = "password123", type="string")
    private String passwordCheck;

    @ExistCategories
    @Schema(
            description = "선호하는 음식 카테고리의 ID 리스트. 각 ID는 유효한 음식 카테고리여야 합니다.",
            example = "[1, 2, 3]",
            type = "array"
    )
    private List<Long> preferCategory;

}
