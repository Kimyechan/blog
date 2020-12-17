package com.rubypaper.service;

import com.rubypaper.domain.user.User;
import com.rubypaper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public String signUp(User user) {
        System.out.println("---> singup 진행");
        if (user.getUserid().length() < 5 && (user.getPassword().length() > 4 && user.getName().length() > 1)){
            return "id";
        } else if(user.getPassword().length() < 5 && (user.getUserid().length() > 4 && user.getName().length() > 1)) {
            return "password";
        } else if (user.getName().length() < 2 && (user.getUserid().length() > 4 && user.getPassword().length() > 4)){
            return "name";
        }

        Optional<User> findUser = userRepository.findByUserid(user.getUserid());

      if (!findUser.isPresent()){
          user.setPassword(passwordEncoder.encode(user.getPassword()));
          userRepository.save(user);
            return "save";
        } else if (user.getUserid() != null && findUser.isPresent()) {
            if (user.getUserid().equals(findUser.get().getUserid())){
                return "duplicated";
            } else {
                return "success";
            }
        } else {
            return "success";
        }
    }

    @Override
    public void withdrawal(User user) {
        System.out.println("---> withdrawal 진행");
        userRepository.deleteById(user.getId());
    }

    @Override
    public void checkRole() {

    }

    @Override
    public void updateUser(User user) {
       User findUser = userRepository.findById(user.getId()).get();
        findUser.setPassword(passwordEncoder.encode(user.getPassword()));
        findUser.setName(user.getName());
        userRepository.save(findUser);

    }

}
