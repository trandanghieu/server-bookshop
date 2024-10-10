package com.tdh.bookstore.service;

import com.tdh.bookstore.model.ProductCategory;
import com.tdh.bookstore.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    // Add a new category
    public ProductCategory addCategory(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    // Update an existing category
    public ProductCategory updateCategory(Long id, ProductCategory category) {
        // Check if the category exists
        if (!productCategoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        category.setId(id); // Ensure the ID is set for update
        return productCategoryRepository.save(category);
    }

    // Delete a category
    public void deleteCategory(Long id) {
        // Optional: Check if the category exists
        if (!productCategoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        productCategoryRepository.deleteById(id);
    }

    // Get a category by ID
    public ProductCategory getCategoryById(Long id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Get all categories
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    // Search categories by name
    public List<ProductCategory> searchCategoriesByName(String name) {
        return productCategoryRepository.findByNameContaining(name);
    }
}
