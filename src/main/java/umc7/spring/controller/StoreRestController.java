package umc7.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.controller.response.ReviewListResponse;
import umc7.spring.controller.response.ReviewResponse;
import umc7.spring.converter.ReviewConverter;
import umc7.spring.dto.ReviewReqDto;
import umc7.spring.service.review.ReviewCommandService;
import umc7.spring.service.review.ReviewQueryService;
import umc7.spring.service.store.StoreQueryService;
import umc7.spring.validation.annotation.ExistStore;

@Tag(name="Store",description = "상점 관련 API")
@Validated
@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreRestController {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;


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
    @Operation(
            summary = "특정 가게 리뷰 목록 조회하기",
            description = "특정 가게에 리뷰 목록을 페이징 처리를 포함하여 조회합니다."
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name="storeId", description = "가게 Id, path variable 입니다.",example = "1"),
            @Parameter(name = "page", description = "조회할 페이지 번호 (1부터 시작).", example = "1", required = true)
    })
    @GetMapping("/{storeId}/review")
    public ApiResponse<ReviewListResponse> getReviewList(
            @PathVariable("storeId") @ExistStore Long storeId,
            @RequestParam(name="page") Integer page
    ) {
        return ApiResponse.onSuccess(ReviewConverter.tpReviewListResponse(reviewQueryService.getReviewList(storeId, page)));
    }
}
