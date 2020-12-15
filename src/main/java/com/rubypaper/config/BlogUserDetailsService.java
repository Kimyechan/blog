package com.rubypaper.config;

import com.rubypaper.domain.user.User;
import com.rubypaper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> findUser = userRepository.findByUserid(username);

        if (!findUser.isPresent()){
            throw new UsernameNotFoundException(username + "사용자가 없습니다");
        } else{
            User user = findUser.get();
            return new SecurityUser(user);
        }


    }

//    @Override
//    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUserid(id);
//
//        if (!user.isPresent()){
//            throw new UsernameNotFoundException(id + "사용자가 없습니다");
//        } else {
//            User user1 = user.get();
//            return new SecurityUser(user1);
//        }
//    }
}
