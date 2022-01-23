package pl.partyka.shopapplication.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.partyka.shopapplication.domain.model.Category;
import pl.partyka.shopapplication.domain.repository.CategoryRepository;
import pl.partyka.shopapplication.dto.CategoryRequest;
import pl.partyka.shopapplication.exceptions.CategoryAlreadyExistsException;
import pl.partyka.shopapplication.exceptions.CategoryDoesNotExistsException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category addOrUpdateCategory(Category category) throws CategoryAlreadyExistsException {
        List<Category> allCategories = categoryRepository.findAll();
        if(allCategories.contains(category)){
            throw new CategoryAlreadyExistsException("Kategoria o podanej nazwie już istnieje",
                    new CategoryRequest(category.getCategoryId(), category.getCategoryName(), category.getProducts()));
        }
        return categoryRepository.save(category);
    }

    public Category getSingleCategory(Integer id) throws CategoryDoesNotExistsException{
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryDoesNotExistsException("Szukana kategoria nie istnieje"));
    }
}
