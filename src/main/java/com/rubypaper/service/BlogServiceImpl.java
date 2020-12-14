package com.rubypaper.service;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

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
    public void registerBlog() {

    }

    @Override
    public void deleteBlog() {

    }

    @Override
    public void updateBlog() {

    }
}
