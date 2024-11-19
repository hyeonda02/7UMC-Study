package umc7.spring.converter;

import umc7.spring.dto.TempResponseDto;

public class TempConverter {
    public static TempResponseDto.TempTestDTO toTempTestDto() {
        return TempResponseDto.TempTestDTO.builder()
                .testString("this is Test")
                .build();
    }
    public static TempResponseDto.TempExceptionDTO toTempExceptionDTO(Integer flag){
        return TempResponseDto.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
