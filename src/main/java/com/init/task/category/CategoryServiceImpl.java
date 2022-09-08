package com.init.task.category;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(CategoryCommand category) {
        return categoryRepository.save(new Category(category.getName(), new ArrayList<>()));
    }

    @Override
    public Optional<Category> editCategoryDetails(Long id, CategoryCommand category) {
        Optional<Category> categoryOptional = findById(id);
        if(categoryOptional.isPresent()){
            return Optional.ofNullable(categoryRepository.save(new Category(category.getName(),
                    categoryOptional.get().getMovies())));
        }else{
            return categoryOptional;
        }
    }
    @Override
    public List<Category> getCategoriesById(List<Long> ids){
        return categoryRepository.findByCategoryIdIn(ids);
    }
}
