package pl.partyka.shopapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import pl.partyka.shopapplication.domain.model.Category;
import pl.partyka.shopapplication.domain.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CategoryRequest {
    private int categoryId;
    @NotNull
    private String categoryName;
    private List<Product> products;

    public Category createCategoryObject(){
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setCategoryId(categoryId);
        category.setProducts(products);
        return category;
    }
}