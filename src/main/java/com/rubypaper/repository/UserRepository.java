package com.rubypaper.repository;

import com.rubypaper.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUserid(String username);

}
