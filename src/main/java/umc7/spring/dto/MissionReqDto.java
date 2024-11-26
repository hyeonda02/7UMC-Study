package umc7.spring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MissionReqDto {
    @NotBlank(message = "주소는 필수 입력 항목입니다. 최대 30자 까지 입력 가능")
    @Size(max = 300)
    @Schema(description = "미션 내용", example = "15000원 이상 식사시 500포인트 지급", type="string")
    private String content;

    @NotNull(message = "미션 마감 날짜는 필수 입력 항목입니다.")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    @Schema(description = "미션 마감 날짜", example = "2024-04-14", type="string")
    private LocalDate deadline;

    @NotNull(message = "미션 성공시 지급되는 포인트 금액은 필수 입력항목입니다.")
    @Schema(description = "포인트", example = "500", type = "int")
    private Integer reward;
}
