package com.agora.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int article_id;

	@JsonBackReference("user-article")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;
	private String title;
	private String description;
	private byte[] image;
	private String publishedAt;
	private String content;
	private int status;
	
	public Article() {
		super();
	}
	public Article(int article_id, User user, String title, String description, byte[] image, String publishedAt, String content,
				   int status) {
		super();
		this.article_id = article_id;
		this.user = user;
		this.title = title;
		this.description = description;
		this.image = image;
		this.publishedAt = publishedAt;
		this.content = content;
		this.status = status;
	}
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int id) {
		this.article_id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle(){return title;}
	public void setTitle(String title) {this.title = title;}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Article article = (Article) o;
		return article_id == article.article_id &&
				status == article.status &&
				Objects.equals(title, article.title) &&
				Objects.equals(description, article.description) &&
				Arrays.equals(image, article.image) &&
				Objects.equals(publishedAt, article.publishedAt) &&
				Objects.equals(content, article.content);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(article_id, title, description, publishedAt, content, status);
		result = 31 * result + Arrays.hashCode(image);
		return result;
	}

	@Override
	public String toString() {
		return "Article{" +
				"article_id=" + article_id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", image=" + Arrays.toString(image) +
				", publishedAt='" + publishedAt + '\'' +
				", content='" + content + '\'' +
				", status=" + status +
				'}';
	}
}
