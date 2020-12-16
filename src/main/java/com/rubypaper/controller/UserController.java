package com.rubypaper.controller;

import com.rubypaper.domain.user.User;
import com.rubypaper.service.BlogService;
import com.rubypaper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@RequiredArgsConstructor
@SessionAttributes({"user", "myBlogCreated"})
public class UserController{
    private final BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(){
        return "blogsystem_search";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("---> login 요청");
        return "login";
    }


    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal(expression = "user") User user, Model model){
        System.out.println("---> loginSuccess 이동");
        System.out.println("---> role " + user.getRole().toString().equals("ROLE_ADMIN"));

        if(blogService.findMyBlog(user.getId()) != null) {
            model.addAttribute("myBlogCreated", true);
        } else {
            model.addAttribute("myBlogCreated", false);
        }

        model.addAttribute("user", user);

        if (user != null && user.getRole().toString().equals("ROLE_ADMIN")){
            return "redirect:/admin"; // 관리자 로그인 임시
        } else if(user != null && user.getRole().toString().equals("ROLE_MEMBER")){
            return "redirect:/user"; // 회원 로그인 임시
        } else {
            return "login"; // 둘 다 아닐 때
        }
    }

    @GetMapping("/accessDenied")
    public void accessDenied(){
        System.out.println("---> accessDenied 이동");
    }

    @GetMapping("/admin")
    public String adminMain(){
        System.out.println("---> adminMain 이동");
        return "redirect:/blog/view/list";
    }

    @GetMapping("/user")
    public String getMemberMain(@AuthenticationPrincipal(expression = "user") User user, Model model){
        System.out.println("---> getMemberMain 이동");
        model.addAttribute("user", user);
        return "blogsystem_search";
    }

    @PostMapping("/user")
    public String memberMain(){
        System.out.println("---> userMain 이동");

        return "redirect:/blog/view/list";

    }

    @GetMapping("/join")
    public void join(){
        System.out.println("---> join 이동");
    }

    @PostMapping("/join")
    public String joinCheck(User user, Model model){
        System.out.println("---> joinSuccess 이동");
        System.out.println("user : " + user.getName());

        model.addAttribute("join", user.getName());
       Boolean joinCheck = userService.signUp(user);
       if (joinCheck){
           return "joinSuccess";
       } else{
           model.addAttribute("retrunJoin", user);
           return "join";
       }

    }

}
