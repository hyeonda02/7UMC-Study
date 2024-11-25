package umc7.spring.converter;

import umc7.spring.domain.Category;
import umc7.spring.domain.mappings.MemberPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {
    public static List<MemberPrefer> toMemPreferList(List<Category> categoryList){
        return categoryList.stream()
                .map(category ->
                        MemberPrefer.builder()
                                .category(category)
                                .build()
                        ).collect(Collectors.toList());
    }
}
