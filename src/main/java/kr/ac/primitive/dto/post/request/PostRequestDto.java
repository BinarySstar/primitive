package kr.ac.primitive.dto.post.request;

import kr.ac.primitive.entity.participant.Participant;
import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.entity.techstack.TechStack;
import lombok.Getter;

import java.util.List;

@Getter
public class PostRequestDto {
    private String title;
    private String summary;
    private String description;
    private String image;
    private boolean isPublic;
    private List<TechStack> techStacks;
    private List<Participant> participants;

    public PostRequestDto(Builder builder) {
        this.title = builder.title;
        this.summary = builder.summary;
        this.description = builder.description;
        this.image = builder.image;
        this.isPublic = builder.isPublic;
        this.techStacks = builder.techStacks;
        this.participants = builder.participants;
    }

    public Post toEntity() {
        return new Post.Builder()
                .title(title)
                .summary(summary)
                .description(description)
                .image(image)
                .isPublic(isPublic)
                .techStacks(techStacks)
                .participants(participants)
                .build();
    }

    public static class Builder {
        private String title;
        private String summary;
        private String description;
        private String image;
        private boolean isPublic;
        private List<TechStack> techStacks;
        private List<Participant> participants;

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

        public Builder techStacks(List<TechStack> techStacks) {
            this.techStacks = techStacks;
            return this;
        }

        public Builder participants(List<Participant> participants) {
            this.participants = participants;
            return this;
        }

        public PostRequestDto build() {
            return new PostRequestDto(this);
        }
    }
}
