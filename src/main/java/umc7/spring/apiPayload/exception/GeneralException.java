package umc7.spring.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc7.spring.apiPayload.code.BaseErrorCode;
import umc7.spring.apiPayload.code.ErrorReasonDto;

@AllArgsConstructor
@Getter
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;
    public ErrorReasonDto getErrorReason() {
        return this.code.getReason();
    }
    public ErrorReasonDto getErrorReasonHttpStatus() {
        return this.code.getReasonHttpStatus();
    }
}
