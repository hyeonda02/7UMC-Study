package umc7.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc7.spring.apiPayload.ApiResponse;
import umc7.spring.converter.TempConverter;
import umc7.spring.dto.TempResponseDto;
import umc7.spring.service.TempService.TempQueryService;

@RestController
@RequestMapping("/temp")
@RequiredArgsConstructor
public class TempRestController {
    private final TempQueryService tmepQueryServiceImpl;
    @GetMapping("/test")
    public ApiResponse<TempResponseDto.TempTestDTO> testApi() {
        return ApiResponse.onSuccess(TempConverter.toTempTestDto());
    }
    @GetMapping("/exception")
    public ApiResponse<TempResponseDto.TempExceptionDTO> exceptionAPI(@RequestParam("flag") Integer flag){
        tmepQueryServiceImpl.CheckFlag(flag);
        return ApiResponse.onSuccess(TempConverter.toTempExceptionDTO(flag));
    }
}

