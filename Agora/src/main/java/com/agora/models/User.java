package com.agora.models;

import java.util.Set;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	
	@OneToMany(targetEntity = Article.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Article> articles;

}
