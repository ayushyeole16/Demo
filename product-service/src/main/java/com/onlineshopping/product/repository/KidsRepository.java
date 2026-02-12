package com.onlineshopping.product.repository;

import com.onlineshopping.product.model.Kids;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KidsRepository extends JpaRepository<Kids, Long> {
}
