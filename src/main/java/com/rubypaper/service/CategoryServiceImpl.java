package com.rubypaper.service;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.category.Category;
import com.rubypaper.repository.BlogRepository;
import com.rubypaper.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final BlogRepository blogRepository;

    @Override
    public void searchCategory() {

    }

    @Override
    public void createCategory() {

    }

    @Override
    public void updateCategory(Category category) {
        Optional<Category> olderCategory = categoryRepository.findById(category.getId());

        Category newCategory = olderCategory.get();
        newCategory.setName(category.getName());
        newCategory.setDisplayType(category.getDisplayType());
        newCategory.setCnt(category.getCnt());
        newCategory.setDescription(category.getDescription());

        categoryRepository.save(newCategory);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public List<Category> getCategories(Long blogId) {
        return categoryRepository.findByBlogId(blogId);
    }

    @Override
    public void saveCategory(Category category, Long myBlogId) {
        Optional<Blog> myBlog = blogRepository.findById(myBlogId);
        category.setBlog(myBlog.get());

        categoryRepository.save(category);
    }
}
