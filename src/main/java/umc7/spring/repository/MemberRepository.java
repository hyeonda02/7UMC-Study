package umc7.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc7.spring.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryCustom {
}
