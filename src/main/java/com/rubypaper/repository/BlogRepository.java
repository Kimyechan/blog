package com.rubypaper.repository;

import com.rubypaper.domain.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByTitle(String searchKeyword);

    @Query("select b from Blog b join fetch b.user where b.user.name = :searchKeyword")
    List<Blog> findByUsername(String searchKeyword);

    List<Blog> findByTag(String searchKeyword);

    @Query("select b from Blog b where b.user.id = :userId")
    Optional<Blog> findByUserId(Long userId);
}
