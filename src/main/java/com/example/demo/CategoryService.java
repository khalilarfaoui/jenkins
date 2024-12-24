package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private List<Category> categories = new ArrayList<>();

    public List<Category> getAllCategories() {
        return categories;
    }

    public Optional<Category> getCategoryById(Long id) {
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Category createCategory(Category category) {
        categories.add(category);
        return category;
    }

    public Category updateCategory(Long id, Category updatedCategory) {
        Optional<Category> existingCategory = getCategoryById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setName(updatedCategory.getName());
            return category;
        }
        return null;
    }

    public boolean deleteCategory(Long id) {
        return categories.removeIf(c -> c.getId().equals(id));
    }
}

