package umc7.spring.apiPayload.exception.handler;

import umc7.spring.apiPayload.code.BaseErrorCode;
import umc7.spring.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode code) {
        super(code);
    }
}
