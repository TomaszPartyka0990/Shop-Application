package pl.partyka.shopapplication.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.partyka.shopapplication.domain.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
