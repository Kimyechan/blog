package com.rubypaper.controller;

import com.rubypaper.domain.user.User;
import com.rubypaper.service.BlogService;
import com.rubypaper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@SessionAttributes({"user", "myBlogCreated"})
public class UserController{
    private final BlogService blogService;

    @Autowired
    private UserService userService;

    // 로그인 뷰로 이동
    @GetMapping("/login")
    public String login(){
        System.out.println("---> login 요청");
        return "login";
    }


    // 로그인 성공해서 role에 따른 페이지 리다이렉션
    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal(expression = "user") User user, Model model){
        System.out.println("---> loginSuccess 이동");

        if(blogService.findMyBlog(user.getId()).isPresent()) {
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

    // 권한 접근 매핑인데 사용 안하는 듯
    @GetMapping("/accessDenied")
    public void accessDenied(){
        System.out.println("---> accessDenied 이동");
    }

    // 관리자 페이지로 이동
    @GetMapping("/admin")
    public String adminMain(){
        System.out.println("---> adminMain 이동");
        return "redirect:/blog/view/list";
    }

    // 회원 페이지로 이동
    @GetMapping("/user")
    public String getMemberMain(@AuthenticationPrincipal(expression = "user") User user, Model model){
        System.out.println("---> getMemberMain 이동");
        model.addAttribute("user", user);
        return "redirect:/blog/view/list";
    }

    @PostMapping("/user")
    public String memberMain(){
        System.out.println("---> userMain 이동");

        return "redirect:/blog/view/list";

    }

    // join 뷰로 이동
    @GetMapping("/join")
    public void join(){
        System.out.println("---> join 이동");
    }

    // 회원가입했을 때 성공 실패에 따라 성공은 joinsueccess.thml로 이동, 실패시 다시 join.html로 이동
    @PostMapping("/join")
    public String joinCheck(@RequestParam("passwordCheck") String passwordCheck, User user, Model model){
        System.out.println("---> joinCheck 이동");

       String joinCheck = userService.signUp(user);
        System.out.println("save :" + joinCheck);
        model.addAttribute("join",user);
       if (joinCheck.equals("success") || joinCheck.equals("save")){
           return "joinSuccess";
       } else if (joinCheck.equals("duplicated")){
           String str = "중복된 아이디 입니다";
           model.addAttribute("message", str);
           return "join";
       } else{

           model.addAttribute("returnJoinPasswordCheck", passwordCheck);
           return "join";
       }

    }

    // mypage로 뷰로 매핑
    @GetMapping("/mypage")
    public void mypage(User user, Model model){
        model.addAttribute("user", user);
    }

    // mypage에서 정보수정
    @PostMapping("/mypage")
    public String mypage(User user){
        userService.updateUser(user);
        return "redirect:/index";
    }

    // 회원탈퇴 페이지로 매핑
    @GetMapping("/withdrawal")
    public void getWithdrawal(User user, Model model){
        model.addAttribute("withdrawal", user);

    }

    // 회원 탈퇴 페이지에서 회원 탈퇴를 눌렀을 때 완전히 회원 탈퇴
    @GetMapping("/withdrawalDo")
    public String postWithdrawal(User user){
        System.out.println("user : " + user.toString());
        userService.withdrawal(user);
        return "redirect:/logout";
    }
}
