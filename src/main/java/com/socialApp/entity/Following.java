package com.socialApp.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// entity class for following
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Following {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	private String firstName;
	private String lastName;
	private String profilePic;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnore
	private User user;
	
	
	
	@Override
	public String toString() {
		return "Following [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", profilePic="
				+ profilePic + ", user=" + user + "]";
	}
	
	
	
	
}
