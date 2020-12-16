package com.rubypaper;

import com.rubypaper.domain.user.User;
import com.rubypaper.repository.UserRepository;
import com.rubypaper.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void signUp() {

//        User user = new User();
//        user.setUserid("manager");
//        user.setPassword(passwordEncoder.encode("manager123")));
//        user.setName("또치");

//        userRepository.save(user);

        User userTest = userRepository.findByUserid("manager").get();
        userTest.setPassword(passwordEncoder.encode("manager123"));
        userService.signUp(userTest);
// {bcrypt}$2a$10$erMry.HU.KyzVs7ku7Cfk.6bNxpOqJh8e18nFcxJIrgRXRAV8EsOe
// {bcrypt}$2a$10$7k9ybLrMJOmVPsD6efXsRO6iO0yvjgGMSJ5bO1neXKEL/iUd9V7lG
    }
}