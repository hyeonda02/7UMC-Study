package umc7.spring.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.controller.response.MissionCompleteResponse;
import umc7.spring.controller.response.MissionResponse;
import umc7.spring.converter.MissionConverter;
import umc7.spring.dto.MissionReqDto;
import umc7.spring.service.mission.MissionCommandService;
import umc7.spring.service.mission.MissionQueryService;
import umc7.spring.validation.annotation.CheckMission;
import umc7.spring.validation.annotation.CheckPage;
import umc7.spring.validation.annotation.ExistStore;

@Tag(name="Mission",description = "미션 관련 API")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/missions")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @Operation(
            summary = "미션 만들기",
            description = "주어진 정보를 바탕으로 가게에 미션을 생성합니다."
    )
    @Parameter(name="storeId",description = "가게 Id, path variable 입니다.",example = "1")
    @PostMapping("/{storeId}/mission")
    public ApiResponse<MissionResponse> createMission(
            @RequestBody @Valid MissionReqDto request,
            @PathVariable("storeId") @ExistStore Long storeId){
        Long memberId = 1L;
        return ApiResponse.onSuccess(new MissionResponse(missionCommandService.createMission(request,storeId,memberId)));
    }

    @Operation(
            summary = "가게의 미션을 도전중인 미션에 추가하기",
            description = "도전하려는 미션이 도전중이 아니라면 가게의 미션을 현재 도전중인 미션으로 추가합니다."
    )
    @Parameter(name="missionId",description = "미션 Id, path variable 입니다.",example = "1")
    @PostMapping("/{missionId}/challenge")
    public ApiResponse<Object> addChallengeMission(
            @CheckMission @PathVariable("missionId") Long missionId
    ){
        Long memberId = 1L;
        return ApiResponse.onSuccess(new MissionCompleteResponse(missionCommandService.addChallenge(missionId, memberId)));
    }
    @Operation(
            summary = "미션 조회하기",
            description = "사용자가 진행중인 미션, 완료한 미션 목록들을 조회합니다."
    )
    @Parameters({
            @Parameter(name="status", description = "진행중인 미션은 false, 완료한 미션은 true 값을 주세요 ",example = "true"),
            @Parameter(name = "page", description = "조회할 페이지 번호 (1부터 시작).", example = "1")
    })
    @PostMapping("")
    public ApiResponse<Object> getMissionList(
            @RequestParam("status") Boolean status,
            @CheckPage Integer page
    ){
        Long memberId = 1L;
        return ApiResponse.onSuccess(MissionConverter.toMissionListResponse( missionQueryService.findMissionsByMemberIdAndStatus(1L, status, page)));
    }


}
