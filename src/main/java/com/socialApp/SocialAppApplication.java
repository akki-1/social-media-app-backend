package com.socialApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.socialApp.entity.Roles;
import com.socialApp.repo.RoleRepo;

@SpringBootApplication
public class SocialAppApplication implements CommandLineRunner {

	@Autowired
	RoleRepo rr;
	public static void main(String[] args) {
		SpringApplication.run(SocialAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Roles role = new Roles();
		role.setRoleId(222);
		role.setRollName("NORMAL");
		
		Roles role1 =new Roles();
		role1.setRoleId(111);
		role1.setRollName("ADMIN");
		
		List<Roles> roles=List.of(role,role1);
		this.rr.saveAll(roles);
		
	}

}
