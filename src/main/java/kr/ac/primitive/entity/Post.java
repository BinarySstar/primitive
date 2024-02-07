package kr.ac.primitive.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    public void update(Post post) {
        if(post.getTitle() != null)
            this.title = post.getTitle();
        if(post.getDescription() != null)
            this.description = post.getDescription();
    }
}
