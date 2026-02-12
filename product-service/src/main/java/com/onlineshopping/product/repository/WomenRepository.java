
package com.onlineshopping.product.repository;

import com.onlineshopping.product.model.Women;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WomenRepository extends JpaRepository<Women, Long> {
}
