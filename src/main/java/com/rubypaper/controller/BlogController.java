package com.rubypaper.controller;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/view/list")
    public String blogView(Model model) {
        Page<Blog> blogPage = blogService.blogListNonCondition();

        List<Blog> blogList = blogPage.getContent();
        model.addAttribute("blogList", blogList);

        return "blogsystem_search";
    }

    @GetMapping("/search")
    public String blog(String searchCondition, String searchKeyword, Model model) {
        List<Blog> blogList = blogService.searchBlog(searchCondition, searchKeyword);

        model.addAttribute("blogList", blogList);
        return "blogsystem_search";
    }

    @GetMapping("/view/create")
    public String blogCreateView(){
        return "blogcreate";
    }

    @PostMapping("/create")
    public String blogCreate(String title, String username, String action) {
        if (action.equals("create")){
            blogService.createBlog(title, username);
        }
        return "redirect:/blog/view/list";
    }

    @GetMapping("/view/myBlog")
    public String myBlog(Long userId, Model model){
        Blog myBlog = blogService.findMyBlog(userId);

        model.addAttribute("myBlog", myBlog);

        return "blogmain_detail";
    }

    @GetMapping("/managing/basic")
    public String manageBlog(Long userId,  Model model) {
        Blog myBlog = blogService.findMyBlog(userId);

        model.addAttribute("myBlog", myBlog);

        return "blogadmin_basic";
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

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
