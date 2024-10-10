package com.tdh.bookstore.repository;

import com.tdh.bookstore.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    // Search for categories by name containing the specified string
    List<ProductCategory> findByNameContaining(String name);
}
