package com.onlineshopping.product.repository;

import com.onlineshopping.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Filter by category
    List<Product> findByCategory(String category);

    // Filter by category and subcategory
    List<Product> findByCategoryAndSubCategory(String category, String subCategory);

    // Get distinct categories
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.category IS NOT NULL")
    List<String> findDistinctCategory();

    // Get distinct subcategories for a category
    @Query("SELECT DISTINCT p.subCategory FROM Product p WHERE p.category = ?1 AND p.subCategory IS NOT NULL")
    List<String> findDistinctSubCategoryByCategory(String category);

    // Search by keyword in product name
    List<Product> findByNameContainingIgnoreCase(String keyword);

    // Optional: Filter by price range
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Optional: Get products with low stock (e.g., less than 10)
    List<Product> findByStockLessThan(Integer threshold);
}
