package com.rubypaper.controller;

import com.rubypaper.domain.comment.Comment;
import com.rubypaper.domain.post.Post;
import com.rubypaper.domain.user.User;
import com.rubypaper.repository.UserRepository;
import com.rubypaper.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
@Lazy
public class PostController {

    @Autowired
    private final PostService postService;
    private final UserRepository userRepository;

    private final int visiblePages = 10; // 보여지는 페이지
    private final int postForPage = 12; // 한 페이지 당 포스트 개수

    // 게시글 전체 목록
    @GetMapping("/list")
    public String getAllPost(Model model) {
        List<Post> postList = postService.getpostList();
        model.addAttribute("postList", postList);

        return "blogmain";
    }

    // 게시글 상세 조회
    @GetMapping("/{postNum}")
    public String getPost(@PathVariable("postNum") Long id, Model model ) {
        // User user = (User)model.getAttribute("user");

        Post postToRead = postService.readPost(id);
        model.addAttribute("post", postToRead);
        model.addAttribute("comment", new Comment());

        return "blogmain_detail";
    }

    // 게시글 입력
    @GetMapping("/newPost")
    public String registerPost() {
//         Optional<Category> category = categoryRepository.findById(categoryId);
//         model.addAttribute("category", category);

        return "blogadmin_write";
    }

    @PostMapping("/addPost.do")
    public String registerPost(Post post, User user) {
        postService.savePost(post);
        return "redirect:/post/list";
    }

    // 게시글 수정
    @GetMapping("/update/{postNum}")
    public String updatePost(@PathVariable("postNum") long id, Model model) {
        Post post = postService.readPost(id);
        model.addAttribute("post", post);

        return "blogadmin_update";
    }

    @PostMapping("/update/{postNum}")
    public String updatePost(Post post) {
        postService.savePost(post);
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
