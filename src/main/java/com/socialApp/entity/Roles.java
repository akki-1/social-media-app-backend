package com.socialApp.entity;


// entity class for roles
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Roles {
		@Id
		private int roleId;
		private String RollName;
		
		public Roles( String rollName) {
			super();
			RollName = rollName;
		}
		
		
	

}
