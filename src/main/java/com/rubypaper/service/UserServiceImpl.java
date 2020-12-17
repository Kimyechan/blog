package com.rubypaper.service;

import com.rubypaper.domain.user.User;
import com.rubypaper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository userRepository;

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }

    @Override
    public boolean signUp(User user) {
        System.out.println("---> singup 진행");
        if (user.getUserid().length() > 5 && user.getPassword().length() > 5 && user.getName().length() > 2) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void deleteUser(User user) {
        System.out.println("---> deleteUser 진행");
        userRepository.deleteById(user.getId());
    }

    @Override
    public void checkRole() {

    }

    @Override
    public String idCheck(String userid) {
        if (userRepository.findUserByUserid(userid).isEmpty()){
            return "YES";
        } else {
            return "NO";
        }
    }

}
