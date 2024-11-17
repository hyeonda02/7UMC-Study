package umc7.spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ReviewDto {

    @Schema(description = "리뷰 내용", example = "맛있어요", type="String")
    private String content;
    @Schema(description = "별점", example = "4.0", type="Float")
    private Float score;

}
