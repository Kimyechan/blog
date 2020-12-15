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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Blog> blog = blogService.findMyBlog(user.getId());

        if(blog.isPresent()) {
            model.addAttribute("blog", blog.get());
            model.addAttribute("isMyBlog", true);
        } else {
            return "redirect:/blog/view/list";
        }

        return "blogmain_detail";
    }

    @GetMapping("/view/selectedBlog")
    public String selectBlog(@RequestParam("id") Long id, Model model) {
        Optional<Blog> selectedBlog = blogService.findBlog(id);
        if(selectedBlog.isEmpty()) {
            return "redirect:/blog/view/list";
        }

        User user = (User) model.getAttribute("user");
        if (user != null) {
            Optional<Blog> myBlog = blogService.findMyBlog(user.getId());
            if (myBlog.isPresent()) {
                if (myBlog.get().getId().equals(id)) {
                    model.addAttribute("isMyBlog", true);
                } else {
                    model.addAttribute("isMyBlog", false);
                }
            } else {
                model.addAttribute("isMyBlog", false);
            }
        } else {
            model.addAttribute("isMyBlog", false);
        }

        model.addAttribute("blog", selectedBlog.get());

        return "blogmain_detail";
    }

    @GetMapping("/managing/basic")
    public String manageBlog(Model model) {
        User user = (User)model.getAttribute("user");

        Optional<Blog> myBlog = blogService.findMyBlog(user.getId());
        if (myBlog.isPresent()) {
            model.addAttribute("myBlog", myBlog.get());
        } else {
            return "redirect:/blgo/view/list";
        }

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
