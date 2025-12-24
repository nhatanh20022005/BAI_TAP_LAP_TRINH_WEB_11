package vn.bt10.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.bt10.dto.CategoryForm;
import vn.bt10.dto.ProductForm;
import vn.bt10.entity.Category;
import vn.bt10.entity.Product;
import vn.bt10.repository.CategoryRepository;
import vn.bt10.repository.ProductRepository;
import vn.bt10.repository.UserRepository;

import java.util.List;

/**
 * Controller providing administration pages.  Only users with the ADMIN role
 * should be allowed to access these URLs.  Admins can view a summary
 * home page, manage categories and manage products.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(CategoryRepository categoryRepository,
                           ProductRepository productRepository,
                           UserRepository userRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {
        long categoryCount = categoryRepository.count();
        long productCount = productRepository.count();
        long userCount = userRepository.count();
        model.addAttribute("categoryCount", categoryCount);
        model.addAttribute("productCount", productCount);
        model.addAttribute("userCount", userCount);
        return "admin/home";
    }

    // Category management
    @GetMapping("/categories")
    public String categories(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("categoryForm", new CategoryForm());
        return "admin/categories";
    }

    @PostMapping("/categories")
    public String createCategory(@ModelAttribute("categoryForm") @Valid CategoryForm form,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/categories";
        }
        Category category = Category.builder()
                .name(form.getName())
                .images(form.getImages())
                .build();
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    // Product management
    @GetMapping("/products")
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("productForm", new ProductForm());
        return "admin/products";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute("productForm") @Valid ProductForm form,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("categories", categoryRepository.findAll());
            return "admin/products";
        }
        Category category = categoryRepository.findById(form.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category"));
        // For simplicity new products are not associated with a specific user; they may default to admin
        Product product = Product.builder()
                .title(form.getTitle())
                .quantity(form.getQuantity())
                .desc(form.getDesc())
                .price(form.getPrice())
                .category(category)
                .build();
        productRepository.save(product);
        return "redirect:/admin/products";
    }
}