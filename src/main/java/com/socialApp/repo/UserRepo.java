package com.socialApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.socialApp.entity.User;

@Component
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUsername(String username);

}
