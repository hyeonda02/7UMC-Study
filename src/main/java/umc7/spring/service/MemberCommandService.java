package umc7.spring.service;

import umc7.spring.domain.Member;
import umc7.spring.dto.MemberReqDto;

public interface MemberCommandService {
    Member signUp(MemberReqDto request);
}
