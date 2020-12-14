package com.rubypaper.controller;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/blogView")
    public String blogView(Model model) {
        Page<Blog> blogPage = blogService.blogListNonCondition();

        List<Blog> blogList = blogPage.getContent();
        model.addAttribute("blogList", blogList);

        return "blogsystem_search";
    }

    @GetMapping("/blogSearch")
    public String blog(String searchCondition, String searchKeyword, Model model) {
        List<Blog> blogList = blogService.searchBlog(searchCondition, searchKeyword);

        model.addAttribute("blogList", blogList);
        return "blogsystem_search";
    }

    @GetMapping("/blogadmin_basic")
    public String blogadmin_basic() {
        return "blogadmin_basic";
    }

    @GetMapping("/blogadmin_category")
    public String blogadmin_category() {
        return "blogadmin_category";
    }

    @GetMapping("/blogadmin_request_remove")
    public String blogadmin_request_remove() {
        return "blogadmin_request_remove";
    }

    @GetMapping("/blogadmin_write")
    public String blogadmin_write() {
        return "blogadmin_write";
    }

    @GetMapping("/blogcreate")
    public String blogcreate() {
        return "blogcreate";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
