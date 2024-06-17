package kr.ac.primitive.entity.post;

import jakarta.persistence.*;
import kr.ac.primitive.dto.post.request.PostRequestDto;
import kr.ac.primitive.entity.participant.Participant;
import kr.ac.primitive.entity.techstack.TechStack;
import kr.ac.primitive.entity.user.User;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter // id는 변하면 안됨
    private Long id;

    private String title;
    private String summary;
    private String description;
    private boolean isPublic;
    private String image;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    @Getter // user는 변하면 안됨
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TechStack> techStacks;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participant> participants;

    public Post() {
    }

    private Post(Builder builder) {
        title = builder.title;
        summary = builder.summary;
        description = builder.description;
        isPublic = builder.isPublic;
        image = builder.image;
        createdAt = builder.createdAt;
        user = builder.user;
        techStacks = builder.techStacks;
        participants = builder.participants;
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.summary = requestDto.getSummary();
        this.description = requestDto.getDescription();
        this.isPublic = requestDto.isPublic();
        this.image = requestDto.getImage();
        this.createdAt = LocalDateTime.now();
        this.techStacks = requestDto.getTechStacks();
        this.participants = requestDto.getParticipants();
    }

    public static class Builder {
        private String title;
        private String summary;
        private String description;
        private boolean isPublic;
        private String image;
        private LocalDateTime createdAt;
        private User user;
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

        public Builder isPublic(boolean isPublic) {
            this.isPublic = isPublic;
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

        public Builder user(User user) {
            this.user = user;
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

        public Post build() {
            return new Post(this);
        }
    }
}