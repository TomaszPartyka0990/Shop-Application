package pl.partyka.shopapplication.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.partyka.shopapplication.domain.model.Category;
import pl.partyka.shopapplication.domain.service.CategoryService;
import pl.partyka.shopapplication.dto.CategoryRequest;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public String getCategoriesView(Model model){
        model.addAttribute("categories", categoryService.getAll());
        return "categories";
    }

    @GetMapping("/add")
    public String getAddCategoryView(Model model){
        if(model.getAttribute("categoryForm") == null) {
            model.addAttribute("categoryForm", new CategoryRequest());
        }
        return "add_category";
    }

    @PostMapping("/add")
    public String addNewCategory(@Valid CategoryRequest categoryRequest, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("categoryForm", categoryRequest);
            return "add_category";
        }
        categoryService.addOrUpdateCategory(categoryRequest.createCategoryObject());
        return "redirect:/categories";
    }

    @GetMapping("/{categoryId}")
    public String getSingleCategoryView(Model model, @PathVariable(name = "categoryId") Integer categoyId){
        Category category = categoryService.getSingleCategory(categoyId);
        model.addAttribute("categoryForm", category);
        return "single_category";
    }

    @PostMapping("/update")
    public String updateCategory(@Valid CategoryRequest categoryRequest, Errors errors, Model model){
        if(errors.hasErrors()){
            model.addAttribute("categoryForm", categoryRequest);
            return "single_category";
        }
        categoryService.addOrUpdateCategory(categoryRequest.createCategoryObject());
        return "redirect:/categories";
    }
}
