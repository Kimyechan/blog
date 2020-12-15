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
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"user", "myBlog"})
public class UserController {
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

        model.addAttribute("myBlog", blogService.findMyBlog(user.getId()));
        model.addAttribute("user", user);

        if (user != null && user.getRole().toString().equals("ROLE_ADMIN")){
            return "redirect:/admin"; // 관리자 로그인 임시
        } else if(user != null && user.getRole().toString().equals("ROLE_MEMBER")){
            return "redirect:/user"; // 회원 로그인 임시
        } else {
            return "login"; // 둘 다 아닐 때
        }
//        return "loginSuccess";
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

    @PostMapping("/user")
    public String memberMain(){
        System.out.println("---> userMain 이동");

        return "redirect:/blog/view/list";

//        return "blogsystem_search";
    }

    @GetMapping("/join")
    public void join(){
        System.out.println("---> join 이동");
    }

    @PostMapping("/joinSuccess")
    public void joinSuccess(User user){
        userService.signUp(user);
        System.out.println("---> joinSuccess 이동");

    }

}
