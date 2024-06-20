package kr.ac.primitive.entity.comment;

import jakarta.persistence.*;
import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    @JoinColumn(name = "users_id")
    @ManyToOne
    private User user;

    @Builder
    public Comment(String content, LocalDateTime createdAt, Post post, User user) {
        this.content = content;
        this.createdAt = createdAt;
        this.post = post;
        this.user = user;
    }
}
