package kr.ac.hansung.cse.exception;

public class CategoryHaveRelationException extends RuntimeException {

    private final Long categoryId;

    public CategoryHaveRelationException(Long id) {
        super("연결된 상품이 있는 카테고리 ID: " + id);
        this.categoryId = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }
}
