package com.rubypaper.service;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.blog.BlogStatus;
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

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
//    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<Blog> blogListNonCondition() {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        return blogRepository.findAllWithUser(pageable);
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
    public void createBlog(String title, User user) {
        Blog blog = Blog.builder()
                .title(title)
                .tag("java")
                .user(user)
                .fileName("j2eelogo.jpg")
                .cntDisplayPost(10)
                .status(BlogStatus.OPERATE)
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
    public void deleteBlog(Long deleteBlogId) {
        blogRepository.deleteById(deleteBlogId);
    }

    @Override
    public void updateBlog() {

    }

    @Override
    public Optional<Blog> findMyBlog(Long userId) {
        return blogRepository.findByUserId(userId);
    }

    @Override
    public Optional<Blog> findBlog(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public void saveBlog(Blog newBlog) {
        blogRepository.save(newBlog);
    }

    @Override
    public List<Blog> findByBlogStatus(BlogStatus blogStatus) {
        return blogRepository.findByStatus(blogStatus);
    }
}
