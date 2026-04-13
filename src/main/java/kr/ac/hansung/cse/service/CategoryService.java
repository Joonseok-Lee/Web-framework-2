package kr.ac.hansung.cse.service;

import kr.ac.hansung.cse.model.Category;
import kr.ac.hansung.cse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /* 카테고리 목록 조회 */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /* 카테고리 등록 */
    @Transactional  //  Persist Entity
    public Category createCategory(Category category) {

        // validation
        if(category.getName() == null) {
            throw new IllegalArgumentException("카테고리 이름은 필수값입니다.");
        }

        return categoryRepository.save(category);
    }

    /* 카테고리 삭제 (연결된 상품 있을 경우 예외 처리) */
    @Transactional  // persist entity
    public void deleteCategory(Long id) {

       long count = categoryRepository.countProductsByCategoryId(id);

       if(count > 0)
           throw new IllegalArgumentException("상품 " + count + "개가 연결되어 있어 삭제할 수 없습니다.");

       categoryRepository.delete(id);
    }
}
