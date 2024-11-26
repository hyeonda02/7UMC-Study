package umc7.spring.service.category;


import java.util.List;

public interface CategoryQueryService {
    boolean categoriesExist(List<Long> categoryList);
}
