package pl.partyka.shopapplication.exceptions;

import lombok.Getter;
import pl.partyka.shopapplication.dto.CategoryRequest;

@Getter
public class CategoryAlreadyExistsException extends RuntimeException {
    private CategoryRequest categoryRequest;
    public CategoryAlreadyExistsException(String message, CategoryRequest categoryRequest) {
        super(message);
        this.categoryRequest = categoryRequest;
    }
}
