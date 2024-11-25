package umc7.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.apiPayload.code.status.ErrorStatus;
import umc7.spring.apiPayload.exception.handler.CategoryHandler;
import umc7.spring.converter.MemberConverter;
import umc7.spring.converter.MemberPreferConverter;
import umc7.spring.domain.Category;
import umc7.spring.domain.Member;
import umc7.spring.domain.mappings.MemberPrefer;
import umc7.spring.dto.MemberRequestDto;
import umc7.spring.repository.CategoryRepository;
import umc7.spring.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCommandServiceImpl implements MemberCommandService{
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Member signUp(MemberRequestDto.JoinDto request) {
        Member newMember = MemberConverter.toMember(request);
        List<Category> categoryList = request.getPreferCategory().stream()
                .map(category->{
                    return categoryRepository.findById(category).orElseThrow(()->new CategoryHandler(ErrorStatus.CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemPreferList(categoryList);
        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});
        return memberRepository.save(newMember);
    }
}
