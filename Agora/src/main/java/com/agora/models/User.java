package com.agora.models;

import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

import com.agora.services.HashingService;
import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;

// this is my test comment

@Entity
@Table(name = "users")
@EntityListeners(HashingService.class)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	private String firstName;
	private String lastName;
	private String userName;

//	The Password is hashed prior to persistence, check the setHashedPassword function in the HashingService class.
//  This also would not be possible without the help of the EntityListener annotation.
	private String password;
	private String email;

//	@JsonManagedReference("user-article")
//	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	private Set<Article> articles;


	public User() {
		super();
	}

	public User(int user_id, String firstName, String lastName, String userName, String password, String email) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
//		this.articles = articles;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int id) {
		this.user_id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Set<Article> getArticles() {
//		return articles;
//	}

//	public void setArticles(Set<Article> articles) {
//		this.articles = articles;
//	}


	@Override
	public String toString() {
		return "User{" +
				"user_id=" + user_id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return user_id == user.user_id &&
				Objects.equals(firstName, user.firstName) &&
				Objects.equals(lastName, user.lastName) &&
				Objects.equals(userName, user.userName) &&
				Objects.equals(password, user.password) &&
				Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(user_id, firstName, lastName, userName, password, email);
	}
}


