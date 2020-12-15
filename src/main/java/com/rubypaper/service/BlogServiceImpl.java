package com.rubypaper.service;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.user.User;
import com.rubypaper.repository.BlogRepository;
import com.rubypaper.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
//    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<Blog> blogListNonCondition() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "cnt");
        return blogRepository.findAll(pageable);
    }

    @Override
    public List<Blog> searchBlog(String searchCondition , String searchKeyword) {
        if (searchCondition.equals("title")) {
            return blogRepository.findByTitle(searchKeyword);
        } else if (searchCondition.equals("blogger")) {
            return blogRepository.findByUsername(searchKeyword);
        } else if (searchCondition.equals("tag")){
            return blogRepository.findByTag(searchKeyword);
        } else {
            return null;
        }
    }

    @Override
    public void createBlog(String title, String username) {
        Blog blog = Blog.builder()
                .title(title)
                .tag("java")
                .fileName("logo.jpg")
                .cnt(0)
                .build();

        blogRepository.save(blog);

        Category category = Category.builder()
                .name("미분류")
                .displayType("미분류")
                .blog(blog)
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void deleteBlog() {

    }

    @Override
    public void updateBlog() {

    }

    @Override
    public Blog findMyBlog(Long userId) {
        return blogRepository.findByUserId(userId);
    }
}
