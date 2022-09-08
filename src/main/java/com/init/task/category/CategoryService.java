package com.init.task.category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);
    Category saveCategory(CategoryCommand category);
    Optional<Category> editCategoryDetails(Long id, CategoryCommand category);
    List<Category> getCategoriesById(List<Long> ids);
}
