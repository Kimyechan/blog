package com.rubypaper.controller;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.user.User;
import com.rubypaper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/blog")
@SessionAttributes({"user", "myBlogCreated"})
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
    public String blogCreate(String title, String action, Model model) {
        if (action.equals("create")){
            User user = (User)model.getAttribute("user");
            blogService.createBlog(title, user);
            model.addAttribute("myBlogCreated", true);
        }
        return "redirect:/blog/view/list";
    }

    @GetMapping("/view/myBlog")
    public String myBlog(Model model){
        User user = (User) model.getAttribute("user");

        Blog myBlog = blogService.findMyBlog(user.getId());
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

}
