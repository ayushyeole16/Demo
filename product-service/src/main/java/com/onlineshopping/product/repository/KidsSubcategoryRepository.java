package com.onlineshopping.product.repository;

import com.onlineshopping.product.model.KidsSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KidsSubcategoryRepository extends JpaRepository<KidsSubcategory, Long> {
}
