package com.socialApp.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// this is our user class, we implement UserDetails here so can at any condition we need to return 
// UserDetails object then we can return just user there. we can create separate class for the implementation of Userdetails too.
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String username;
	private String password;
	private String email;
	private String gender;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	private List<Followers> followers;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Following> following;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<UserImages> images;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "user_role")
	private Set<Roles> userRoles = new HashSet<>();
	private boolean enabled = true;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<SimpleGrantedAuthority> auths = this.userRoles.stream()
				.map((role) -> new SimpleGrantedAuthority(role.getRollName())).collect(Collectors.toList());
		return auths;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

}
