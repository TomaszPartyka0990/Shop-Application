package pl.partyka.shopapplication.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.partyka.shopapplication.domain.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
