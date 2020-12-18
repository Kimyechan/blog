package com.rubypaper.controller;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.blog.BlogStatus;
import com.rubypaper.domain.user.User;
import com.rubypaper.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/blog")
@SessionAttributes({"user", "myBlogCreated", "myBlogId", "blog"})
public class BlogController {

    private final BlogService blogService;

    @GetMapping("/view/list")
    public String blogView(Model model) {
        Page<Blog> blogPage = blogService.blogListNonCondition();

        List<Blog> blogList = blogPage.getContent();
        model.addAttribute("blogList", blogList);
        User user = (User) model.getAttribute("user");
        if(user != null) {
            if (blogService.findMyBlog(user.getId()).isPresent()) {
                model.addAttribute("myBlogCreated", true);
            } else {
                model.addAttribute("myBlogCreated", false);
            }
        }

        return "blogsystem_search";
    }

    @GetMapping("/search")
    public String blog(String searchCondition, String searchKeyword, Model model) {
        if (searchKeyword == null) {
            searchKeyword = "";
        }

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
            model.addAttribute("myBlogId", blog.get().getId());
            model.addAttribute("blog", blog.get());
            model.addAttribute("postList", blog.get().getPostList());
            model.addAttribute("categoryList", blog.get().getCategories());
            model.addAttribute("isMyBlog", true);
            if(!blog.get().getPostList().isEmpty()) {
                String writeUserName = blog.get().getPostList().get(0).getBlog().getUser().getName();
                model.addAttribute("writeUserName", writeUserName);
            }
        } else {
            return "redirect:/blog/view/list";
        }

        return "blogmain";
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
                    model.addAttribute("myBlogId", myBlog.get().getId());
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
        model.addAttribute("categoryList", selectedBlog.get().getCategories());
        model.addAttribute("postList", selectedBlog.get().getPostList());
        if(!selectedBlog.get().getPostList().isEmpty()) {
            String writeUserName = selectedBlog.get().getPostList().get(0).getBlog().getUser().getName();
            model.addAttribute("writeUserName", writeUserName);
        }

        return "blogmain";
    }

    @GetMapping("/managing/basic")
    public String manageBlog(Model model) {
        User user = (User)model.getAttribute("user");
        if(user == null) {
            return "redirect:/blog/view/list";
        }
        Optional<Blog> myBlog = blogService.findMyBlog(user.getId());
        if (myBlog.isPresent()) {
            model.addAttribute("blog", myBlog.get());
        } else {
            return "redirect:/blog/view/list";
        }

        return "blogadmin_basic";
    }

    @PostMapping("/update")
    public String update(MultipartFile file, @Valid Blog blog, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            return "blogadmin_basic";
        }
        Optional<Blog> olderBlog = blogService.findBlog(blog.getId());
        if (olderBlog.isEmpty()) {
            return "redirect:/blog/view/list";
        }

        if (file.isEmpty()) {
            return "redirect:/blog/managing/basic";
        }

        File temp = new File("");

        String saveDirectory = temp.getAbsolutePath() + "/src/main/resources/static/images/logo/";
        String fileName = file.getOriginalFilename();//getting file name
        System.out.println("directory with file name: " + saveDirectory+fileName);
        file.transferTo(new File(saveDirectory + fileName));

        Blog newBlog = olderBlog.get();
        newBlog.setTitle(blog.getTitle());
        newBlog.setTag(blog.getTag());
        newBlog.setCntDisplayPost(blog.getCntDisplayPost());
        newBlog.setFileName(fileName);

        blogService.saveBlog(newBlog);

        return "redirect:/blog/managing/basic";
    }

    @GetMapping("/view/delete")
    public String deleteView() {
        return "blogadmin_request_remove";
    }

    @PostMapping("/deleteRequest")
    public String deleteRequestProcess(String action, Model model) {
        if (action.equals("동의")) {
            Long blogId = (Long) model.getAttribute("myBlogId");
            Optional<Blog> blog = blogService.findBlog(blogId);
            Blog deleteBlog = blog.get();

            deleteBlog.setStatus(BlogStatus.REMOVE_REQUEST);
            blogService.saveBlog(deleteBlog);
        } else if (action.equals("취소")) {
            return "redirect:/blog/managing/basic";
        }

        return "redirect:/blog/view/list";
    }

    @GetMapping("/view/deleteList")
    public String deleteListView(Model model) {
        List<Blog> deleteList = blogService.findByBlogStatus(BlogStatus.REMOVE_REQUEST);
        model.addAttribute("deleteList", deleteList);
        return "blogsystem_manage";
    }

    @PostMapping("/delete")
    public String deleteProcess(Long deleteBlogId) {
        blogService.deleteBlog(deleteBlogId);
        return "redirect:/blog/view/deleteList";
    }
}
