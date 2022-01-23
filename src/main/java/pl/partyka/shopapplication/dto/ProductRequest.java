package pl.partyka.shopapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import pl.partyka.shopapplication.domain.model.Category;
import pl.partyka.shopapplication.domain.model.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductRequest {
    private int productId;
    @Length(max = 40)
    private String productName;
    private String productDescription;
    private Category category;
    private String imagePath;
    private double prize;

    public Product createProductObject(){
        Product product= new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductDescription(productDescription);
        product.setCategory(category);
        product.setImagePath(imagePath);
        product.setPrize(prize);
        return product;
    }
}
