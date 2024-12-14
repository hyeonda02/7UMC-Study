package umc7.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.controller.response.MemberResponse;
import umc7.spring.controller.response.ReviewListResponse;
import umc7.spring.converter.ReviewConverter;
import umc7.spring.dto.MemberReqDto;
import umc7.spring.service.member.MemberCommandService;
import umc7.spring.service.review.ReviewQueryService;
import umc7.spring.validation.annotation.CheckPage;
import umc7.spring.validation.annotation.ExistStore;

@Tag(name="Member",description = "회원 관련 API")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final ReviewQueryService reviewQueryService;
    @Operation(
            summary = "회원 가입",
            description = "주어진 정보를 바탕으로 회원가입 요청을 처리합니다."
    )
    @PostMapping("/signup")
    public ApiResponse<MemberResponse> signup(
            @RequestBody @Valid MemberReqDto request) {
        return ApiResponse.onSuccess(new MemberResponse(memberCommandService.signUp(request)));
    }

    @Operation(
            summary = "리뷰 목록 조회",
            description = "내가 작성한 리뷰 목록을 조회합니다."
    )
    @Parameters({
            @Parameter(name = "page", description = "조회할 페이지 번호 (1부터 시작).", example = "1", required = true)
    })
    @GetMapping("/reviews")
    public ApiResponse<ReviewListResponse> getReviewListWithMember(
            @CheckPage Integer page
    ){
        //회원 가입 로직 구현시 memberId 부분 변경 예정
        return ApiResponse.onSuccess(ReviewConverter.toReviewListResponse(
                reviewQueryService.getReviewListByMemberId(1L, page)));
    }


}
