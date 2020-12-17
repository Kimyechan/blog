package com.rubypaper.repository;

import com.rubypaper.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.blog.id = :blogId")
    List<Category> findByBlogId(Long blogId);
}
