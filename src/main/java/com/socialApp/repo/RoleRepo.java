package com.socialApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialApp.entity.Roles;

public interface RoleRepo extends JpaRepository<Roles, Integer> {

}
