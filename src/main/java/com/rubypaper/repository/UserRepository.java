package com.rubypaper.repository;

import com.rubypaper.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserid(String username);

    @Query ("select u.userid from User as u where u.userid = :userid")
    Optional<User> findUserByUserid(String userid);




}
