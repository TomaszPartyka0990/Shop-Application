package pl.partyka.shopapplication.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.partyka.shopapplication.domain.model.Product;
import pl.partyka.shopapplication.domain.repository.ProductRepository;
import pl.partyka.shopapplication.dto.ProductRequest;
import pl.partyka.shopapplication.exceptions.ProductAlreadyExistsException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product addOrUpdateProduct(Product product) throws ProductAlreadyExistsException {
        List<Product> allProducts = productRepository.findAll();
        if(allProducts.contains(product)){
            throw new ProductAlreadyExistsException("Produkt o podanej nazwie już istnieje", new ProductRequest());
        }
        return productRepository.save(product);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
