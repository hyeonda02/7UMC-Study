package umc7.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.controller.response.ReviewResponse;
import umc7.spring.dto.ReviewReqDto;
import umc7.spring.service.review.ReviewCommandService;
import umc7.spring.validation.annotation.ExistStore;

@Tag(name="Store",description = "상점 관련 API")
@Validated
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {
    private final ReviewCommandService reviewCommandService;


    @Operation(
            summary = "리뷰 작성하기",
            description = "특정 가게에 리뷰를 추가합니다."
    )
    @Parameter(name="storeId", description = "가게 Id, path variable 입니다.",example = "1")
    @PostMapping("/{storeId}/review")
    public ApiResponse<ReviewResponse> createReview(
            @PathVariable("storeId") @ExistStore Long storeId,
            @RequestBody @Valid ReviewReqDto request) {
        Long memberId = 1L;
        return ApiResponse.onSuccess(new ReviewResponse(reviewCommandService.createReview(request, storeId, memberId)));
    }
}
