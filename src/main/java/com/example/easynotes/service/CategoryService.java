package com.example.easynotes.service;

import com.example.easynotes.model.Category;
import com.example.easynotes.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by JS.
 */

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    // Get all
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    // Create a category
    public Category createCategory(Category category){
        return categoryRepository.save(category);
    }

    // Get a single category
    public Category getCategory(Long categoryId){
        Category category = categoryRepository.findOne(categoryId);
        if(category==null){
            return null;
        }
        return category;
    }

    // Update a category
    public Category updateCategory(Long categoryId, Category freshCategory){
        Category category = categoryRepository.findOne(categoryId);
        if(category==null){
            return null;
        }
        category.setName(freshCategory.getName());

        Category updatedCategory = categoryRepository.save(category);
        return updatedCategory;
    }

    // Delete a category
    public Boolean deleteCategory(Long categoryId){
        Category category = categoryRepository.findOne(categoryId);
        if(category==null){
            return false;
        }

        categoryRepository.delete(category);
        return true;
    }
}