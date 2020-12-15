package com.rubypaper.repository;

import com.rubypaper.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

//    Optional<User> findByid(Long id);
    Optional<User> findByUserid(String username);
}
