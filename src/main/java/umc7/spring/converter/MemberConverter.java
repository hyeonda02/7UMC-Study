package umc7.spring.converter;

import umc7.spring.domain.Member;
import umc7.spring.domain.enums.Gender;
import umc7.spring.dto.MemberReqDto;

import java.util.ArrayList;

public class MemberConverter {
    public static Member toMember(MemberReqDto request) {
        Gender gender = null;
        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
        }
        return Member.builder()
                .name(request.getName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .password(request.getPassword())
                .email(request.getEmail())
                .memberPreferList(new ArrayList<>())
                .pointTransactionList(new ArrayList<>())
                .reviewList(new ArrayList<>())
                .questionList(new ArrayList<>())
                .build();
    }
}
