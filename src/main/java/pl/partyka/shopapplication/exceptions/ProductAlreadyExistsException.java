package pl.partyka.shopapplication.exceptions;

import lombok.Getter;
import pl.partyka.shopapplication.dto.ProductRequest;

@Getter
public class ProductAlreadyExistsException extends RuntimeException {
    private ProductRequest productRequest;

    public ProductAlreadyExistsException(String message, ProductRequest productRequest) {
        super(message);
        this.productRequest = productRequest;
    }
}
