package umc7.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.domain.response.MemberResponse;
import umc7.spring.dto.MemberRequestDto;
import umc7.spring.service.MemberCommandService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberCommandService memberCommandService;

    @Operation(
            summary = "회원 가입",
            description = "주어진 정보를 바탕으로 회원가입 요청을 처리합니다."
    )
    @PostMapping("/signup")
    public ApiResponse<MemberResponse> signup(
            @RequestBody @Valid MemberRequestDto.JoinDto request) {
        return ApiResponse.onSuccess(new MemberResponse(memberCommandService.signUp(request)));
    }

}
