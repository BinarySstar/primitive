package kr.ac.primitive.entity.post;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.ac.primitive.dto.post.request.PostRequestDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String summary;
    private String description;
    private Boolean isPublic;
    private String image;
    private LocalDateTime createdAt;

    public Post() {
    }

    private Post(Builder builder) {
        title = builder.title;
        summary = builder.summary;
        description = builder.description;
        isPublic = builder.isPublic;
        image = builder.image;
        createdAt = LocalDateTime.now();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.summary = requestDto.getSummary();
        this.description = requestDto.getDescription();
        this.isPublic = requestDto.getIsPublic();
        this.image = requestDto.getImage();
        this.createdAt = LocalDateTime.now();
    }

    public static class Builder {
        private String title;
        private String summary;
        private String description;
        private Boolean isPublic;
        private String image;

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder isPublic(Boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}