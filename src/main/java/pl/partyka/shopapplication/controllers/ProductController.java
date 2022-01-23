package pl.partyka.shopapplication.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.partyka.shopapplication.domain.model.Category;
import pl.partyka.shopapplication.domain.service.CategoryService;
import pl.partyka.shopapplication.domain.service.ProductService;
import pl.partyka.shopapplication.dto.ProductRequest;
import pl.partyka.shopapplication.utils.FileUploadUtil;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService productService;

    @GetMapping
    public String listAllProducts(Model model){
        model.addAttribute("allProducts", productService.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String getProductAddView(Model model){
        if(model.getAttribute("productForm") == null) {
            model.addAttribute("productForm", new ProductRequest());
        }
        model.addAttribute("allCategories", categoryService.getAll());
        return "add_product";
    }

    @PostMapping("/add")
    public String addNewProduct(@Valid ProductRequest productRequest, Errors errors, Model model,
                                @RequestParam("image")MultipartFile multipartFile,
                                @RequestParam("categoryId") int categoryId) throws IOException {
        if(errors.hasErrors()){
            model.addAttribute("productForm", productRequest);
            model.addAttribute("allCategories", categoryService.getAll());
            return "add_product";
        }
        if (multipartFile.getOriginalFilename()!=null) {
            String uploadedFilename = multipartFile.getOriginalFilename();
            String filename = productRequest.getProductName() + uploadedFilename.substring(uploadedFilename.lastIndexOf("."));
            String uploadDir = "src/main/webapp/WEB-INF/product-photos";
            FileUploadUtil.saveFile(uploadDir, filename, multipartFile);
            String filePath = "product-photos/" + filename;
            productRequest.setImagePath(filePath);
        }
        Category singleCategory = categoryService.getSingleCategory(categoryId);
        productRequest.setCategory(singleCategory);
        productService.addOrUpdateProduct(productRequest.createProductObject());
        return "redirect:/";
    }
}
