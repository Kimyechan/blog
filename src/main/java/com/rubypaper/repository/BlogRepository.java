package com.rubypaper.repository;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.blog.BlogStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(value = "select b from Blog b join fetch b.user",
            countQuery = "select count(b.user) from Blog b")
    Page<Blog> findAllWithUser(Pageable pageable);

    List<Blog> findByTitle(String searchKeyword);

    @Query("select b from Blog b join fetch b.user where b.user.name = :searchKeyword")
    List<Blog> findByUsername(String searchKeyword);

    List<Blog> findByTagContaining(String searchKeyword);

    @Query("select b from Blog b where b.user.id = :userId")
    Optional<Blog> findByUserId(Long userId);

    List<Blog> findByStatus(BlogStatus blogStatus);
}
