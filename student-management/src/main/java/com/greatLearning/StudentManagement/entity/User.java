package com.greatLearning.StudentManagement.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "users_roles",
			joinColumns = @JoinColumn(name = "user_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private List<Role> roles = new ArrayList<>();
	
	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
		
	public void addRole(Role userRole) {
		roles.add(userRole);
	}
}
		




