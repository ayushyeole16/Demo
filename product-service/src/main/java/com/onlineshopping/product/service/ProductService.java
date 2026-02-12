package com.onlineshopping.product.service;

import com.onlineshopping.product.model.Product;
import com.onlineshopping.product.repository.ProductRepository;
import com.onlineshopping.product.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Add new product
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Update existing product
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        if (existing == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setStock(updatedProduct.getStock());
        existing.setCategory(updatedProduct.getCategory());
        existing.setSubCategory(updatedProduct.getSubCategory());
        return productRepository.save(existing);
    }

    // Delete product
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // Filter by category
    public List<Product> filterByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Filter by category and subcategory
    public List<Product> filterByCategoryAndSubCategory(String category, String subCategory) {
        return productRepository.findByCategoryAndSubCategory(category, subCategory);
    }

    // Get all categories
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategory();
    }

    // Get subcategories for a category
    public List<String> getSubCategoriesByCategory(String category) {
        return productRepository.findDistinctSubCategoryByCategory(category);
    }

    // Search by keyword
    public List<Product> searchByKeyword(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    // Optional: Get products with low stock
    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findByStockLessThan(threshold);
    }

    // Optional: Filter by price range
    public List<Product> getProductsByPriceRange(double min, double max) {
        return productRepository.findByPriceBetween(min, max);
    }
}
