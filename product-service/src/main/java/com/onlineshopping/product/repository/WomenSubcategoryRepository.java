package com.onlineshopping.product.repository;

import com.onlineshopping.product.model.WomenSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WomenSubcategoryRepository extends JpaRepository<WomenSubcategory, Long> {
}
