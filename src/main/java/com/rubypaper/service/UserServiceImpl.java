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
    public void signUp(User user) {
        System.out.println("---> singup 진행");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }

    @Override
    public void deleteUser(User user) {
        System.out.println("---> deleteUser 진행");
        userRepository.deleteById(user.getId());
    }

    @Override
    public void checkRole() {

    }
}
