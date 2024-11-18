package umc7.spring.service;

import umc7.spring.domain.Member;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long memberId);
}
