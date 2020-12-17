package com.rubypaper.service;


import com.rubypaper.domain.category.Category;

import java.util.List;

public interface CategoryService {

    void searchCategory();

    void createCategory();

    void updateCategory(Category category);

    void deleteCategory(Long categoryId);

    List<Category> getCategories(Long blogId);

    void saveCategory(Category category, Long myBlogId);
}
