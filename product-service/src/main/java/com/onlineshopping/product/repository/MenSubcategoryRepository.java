package com.onlineshopping.product.repository;

import com.onlineshopping.product.model.MenSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenSubcategoryRepository extends JpaRepository<MenSubcategory, Long> {
}
