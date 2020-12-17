package com.rubypaper.controller;

import com.rubypaper.domain.blog.Blog;
import com.rubypaper.domain.category.Category;
import com.rubypaper.domain.comment.Comment;
import com.rubypaper.domain.post.Post;
import com.rubypaper.domain.user.User;
import com.rubypaper.repository.CategoryRepository;
import com.rubypaper.repository.UserRepository;
import com.rubypaper.service.BlogService;
import com.rubypaper.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
@SessionAttributes({"user", "blog", "isMyBlog"})
public class PostController {

    @Autowired
    private final PostService postService;
    private final BlogService blogService;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private final int visiblePages = 10; // 보여지는 페이지
    private final int postForPage = 12; // 한 페이지 당 포스트 개수

    // 게시글 전체 목록
    @GetMapping("/list")
    public String getAllPost(Model model) {
        User user = (User) model.getAttribute("user");
        List<Post> postList = postService.getpostList();

        model.addAttribute("postList", postList);
        model.addAttribute("user", user);

        return "blogmain";
    }

    // 게시글 상세 조회
    @GetMapping("/{postNum}")
    public String getPost(@PathVariable("postNum") Long id, Model model ) {
        User user = (User)model.getAttribute("user");
        Optional<Blog> blog = blogService.findMyBlog(user.getId());
        Post postToRead = postService.readPost(id);

        model.addAttribute("post", postToRead);
        model.addAttribute("comment", new Comment());
        model.addAttribute("user", user);
        model.addAttribute("isMyBlog", true);
        model.addAttribute("blog", blog.get());
        model.addAttribute("categoryList", blog.get().getCategories());

        return "blogmain_detail";
    }

    // 게시글 입력
    @GetMapping("/newPost")
    public String registerPost(Model model) {
        User user = (User)model.getAttribute("user");
        Optional<Blog> blog = blogService.findMyBlog(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("isMyBlog", true);
        model.addAttribute("blog", blog.get());
        model.addAttribute("categoryList", blog.get().getCategories());

        return "blogadmin_write";
    }

    @PostMapping("/addPost.do")
    public String registerPost(Post post, User user, Long categoryId) {
        Post savePost = postService.savePost(post, categoryId);

        
//        Long category = Long.valueOf(request.getParameter("category"));
//
//        newCategory.setId(category);
        //String categoryId = request.getParameter("categoryId");
        //Category category = new Category();
        //category.setId(Long.valueOf(categoryId));
        //Category newCategory = new Category();
        //postService.savePost(post, newCategory);
        return "redirect:/post/list";
    }

    // 게시글 수정
    @GetMapping("/update/{postNum}")
    public String updatePost(@PathVariable("postNum") long id, Model model) {
        Post post = postService.readPost(id);
        model.addAttribute("post", post);

        return "blogadmin_update";
    }

    @PostMapping(value = {"/update/{postNum}"})
    public String updatePost(Post post, HttpServletRequest request) {
        Long categoryId = Long.valueOf(request.getParameter("categorytitle"));
        Category category = new Category();
        category.setId(categoryId);

        //postService.savePost(post, category);
        postService.savePost(post, categoryId);
        return "redirect:/post/" + post.getId();

        /* 관리자 / 사용자 구분
        boolean isUser;
        boolean isAdmin;
        if (!isAdmin && !isUser) {
            throw new RuntimeException("권한 없음~");
        }
         */
    }

    // 게시글 삭제
    @GetMapping("/delete/{postNum}")
    public String deletePost(@PathVariable("postNum") Long id) {
        postService.deletePost(id);
        return "redirect:/post/list";
    }

}
