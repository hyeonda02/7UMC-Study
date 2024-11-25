package umc7.spring.service;


import java.util.List;

public interface CategoryQueryService {
    boolean categoriesExist(List<Long> categoryList);
}
