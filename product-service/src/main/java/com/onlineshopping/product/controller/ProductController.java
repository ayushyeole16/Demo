package com.onlineshopping.product.controller;

import com.onlineshopping.product.dto.Productdto;
import com.onlineshopping.product.model.Product;
import com.onlineshopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public List<Productdto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Productdto getProductById(@PathVariable Long id) {
        return convertToDto(productService.getProductById(id));
    }

    // Add new product
    @PostMapping
    public Productdto addProduct(@RequestBody Productdto productDto) {
        Product product = convertToEntity(productDto);
        return convertToDto(productService.addProduct(product));
    }

    // Update product
    @PutMapping("/{id}")
    public Productdto updateProduct(@PathVariable Long id, @RequestBody Productdto productDto) {
        Product product = convertToEntity(productDto);
        return convertToDto(productService.updateProduct(id, product));
    }

    // Delete product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    // Filter by category and optional subcategory
    @GetMapping("/filter")
    public List<Productdto> filterByCategory(@RequestParam String category, @RequestParam(required = false) String subCategory) {
        List<Product> products;
        if (subCategory != null && !subCategory.isEmpty()) {
            products = productService.filterByCategoryAndSubCategory(category, subCategory);
        } else {
            products = productService.filterByCategory(category);
        }
        return products.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get all categories
    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    // Get subcategories for a category
    @GetMapping("/categories/{category}/subcategories")
    public List<String> getSubCategoriesByCategory(@PathVariable String category) {
        return productService.getSubCategoriesByCategory(category);
    }

    // Search by keyword
    @GetMapping("/search")
    public List<Productdto> searchByKeyword(@RequestParam String keyword) {
        return productService.searchByKeyword(keyword)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Optional: Get low stock products
    @GetMapping("/low-stock")
    public List<Productdto> getLowStockProducts(@RequestParam(defaultValue = "10") int threshold) {
        return productService.getLowStockProducts(threshold)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Optional: Filter by price range
    @GetMapping("/price-range")
    public List<Productdto> getProductsByPriceRange(@RequestParam double min, @RequestParam double max) {
        return productService.getProductsByPriceRange(min, max)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Utility: Convert Entity to DTO
    private Productdto convertToDto(Product product) {
        return new Productdto(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getSubCategory(),
                product.getImg()
        );
    }

    // Utility: Convert DTO to Entity
    private Product convertToEntity(Productdto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());
        product.setSubCategory(dto.getSubCategory());
        product.setImg(dto.getImg());
        return product;
    }
}
