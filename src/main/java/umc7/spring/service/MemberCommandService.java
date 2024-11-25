package umc7.spring.service;

import umc7.spring.domain.Member;
import umc7.spring.dto.MemberRequestDto;

public interface MemberCommandService {
    Member signUp(MemberRequestDto.JoinDto request);
}
