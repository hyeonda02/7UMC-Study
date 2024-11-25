package umc7.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc7.spring.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryQueryServiceImpl implements CategoryQueryService{
    private final CategoryRepository categoryRepository;
    @Override
    public boolean categoriesExist(List<Long> categoryList) {
        return categoryList.stream().allMatch(categoryRepository::existsById);
    }
}
