package com.rubypaper.repository;

import com.rubypaper.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.blog.id = :blogId")
    List<Category> findByBlogId(Long blogId);

    @Query("select c from Category c join fetch c.blog where c.id = :categoryId")
    Optional<Category> findWithBlogByCategoryId(Long categoryId);
}
