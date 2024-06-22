package kr.ac.primitive.dto.post.response;

import kr.ac.primitive.entity.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String summary;
    private String image;
    private LocalDateTime createdAt;

    public PostResponseDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.summary = builder.summary;
        this.image = builder.image;
        this.createdAt = builder.createdAt;
    }
    public static PostResponseDto toDto(Post post) {
        return new PostResponseDto.Builder()
                .id(post.getId())
                .title(post.getTitle())
                .summary(post.getSummary())
                .image(post.getImage())
                .createdAt(post.getCreatedAt())
                .build();
    }

    public static class Builder {
        private Long id;
        private String title;
        private String summary;
        private String image;
        private LocalDateTime createdAt;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PostResponseDto build() {
            return new PostResponseDto(this);
        }
    }
}
