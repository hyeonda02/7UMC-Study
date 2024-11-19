package umc7.spring.apiPayload.code.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc7.spring.apiPayload.code.BaseCode;
import umc7.spring.apiPayload.code.ReasonDto;


@Getter
@RequiredArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "200 Request", "요청입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .isSuccess(true)
                .code(code)
                .message(message).build();

    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .httpStatus(httpStatus)
                .build();
    }
}
