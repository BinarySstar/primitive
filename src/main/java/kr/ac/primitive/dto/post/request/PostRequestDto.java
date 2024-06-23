package kr.ac.primitive.dto.post.request;

import kr.ac.primitive.entity.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String summary;
    private String description;
    private String image;
    private Boolean isPublic;

    public PostRequestDto(Builder builder) {
        this.title = builder.title;
        this.summary = builder.summary;
        this.description = builder.description;
        this.image = builder.image;
        this.isPublic = builder.isPublic;
    }

    public Post toEntity() {
        return new Post.Builder()
                .title(title)
                .summary(summary)
                .description(description)
                .image(image)
                .isPublic(isPublic)
                .build();
    }

    public static class Builder {
        private String title;
        private String summary;
        private String description;
        private String image;
        private Boolean isPublic;

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

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder isPublic(boolean isPublic) {
            this.isPublic = isPublic;
            return this;
        }

        public PostRequestDto build() {
            return new PostRequestDto(this);
        }
    }
}
