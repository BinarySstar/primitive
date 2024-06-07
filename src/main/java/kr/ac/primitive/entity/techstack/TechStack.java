package kr.ac.primitive.entity.techstack;

import jakarta.persistence.*;
import kr.ac.primitive.entity.post.Post;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String techName;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public TechStack() {
    }

    public TechStack(String techName, Post post) {
        this.techName = techName;
        this.post = post;
    }
}
