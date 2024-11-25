package umc7.spring.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDto {
    @Getter
    public static class JoinDto{
        String name;
        String address;
        String email;
        Integer gender;
        String phone;
        String password;
        String passwordCheck;
        List<Long> preferCategory;


    }
}
