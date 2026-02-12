package com.onlineshopping.product.repository;

import com.onlineshopping.product.model.Men;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenRepository extends JpaRepository<Men, Long> {
}
