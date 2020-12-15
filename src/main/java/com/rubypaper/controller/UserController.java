package com.rubypaper.controller;

import com.rubypaper.domain.user.User;
import com.rubypaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

//    @GetMapping("/")
//    public String index(){
//        return "blogsystem_search";
//    }

    @GetMapping("/login")
    public String login(){
        System.out.println("---> login 요청");
        return "login";
    }

//    @PostMapping("/login")
//    public String login(User user, Model model){
//        model.addAttribute("user",user);
//        return "redirect:/loginSuccess";
//
//    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(User user, Model model){
        System.out.println("---> loginSuccess 이동");

        if (user != null && user.getRole().equals("ADMIN")){
            model.addAttribute("user", user);
            return "redirect:/admin"; // 관리자 로그인 임시
        } else if(user != null && user.getRole().equals("User")){
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
        return "blogadmin_basic";
    }

    @PostMapping("/user")
    public String memberMain(){
        System.out.println("---> userMain 이동");
        return "blogsystem_search";
    }

}
