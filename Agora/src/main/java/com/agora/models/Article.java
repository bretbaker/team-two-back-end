package com.agora.models;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	private User user;
	private String description;
	private byte[] image;
	private String publishedAt;
	private String content;
	private int status;

}
