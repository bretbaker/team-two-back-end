package com.agora.models;

import java.util.Arrays;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int article_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + article_id;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((publishedAt == null) ? 0 : publishedAt.hashCode());
		result = prime * result + status;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (article_id != other.article_id)
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (publishedAt == null) {
			if (other.publishedAt != null)
				return false;
		} else if (!publishedAt.equals(other.publishedAt))
			return false;
		if (status != other.status)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Article [id=" + article_id + ", user=" + user + ", description=" + description + ", image="
				+ Arrays.toString(image) + ", publishedAt=" + publishedAt + ", content=" + content + ", status="
				+ status + "]";
	}

	
	
}
