package com.agora.models;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class PublishArticleTemplate {

    private int user_id;
    private String title;
    private String description;
    private byte[] image;
    private LocalDateTime publishedAt;
    private String content;
    private int status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishArticleTemplate that = (PublishArticleTemplate) o;
        return user_id == that.user_id &&
                status == that.status &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Arrays.equals(image, that.image) &&
                Objects.equals(publishedAt, that.publishedAt) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(user_id, title, description, publishedAt, content, status);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @Override
    public String toString() {
        return "PublishArticleTemplate{" +
                "user_id=" + user_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image=" + Arrays.toString(image) +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                ", status=" + status +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
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
}
