package kr.ac.primitive.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    public void update(Post post) {
        if(post.getTitle() != null)
            this.title = post.getTitle();
        if(post.getDescription() != null)
            this.description = post.getDescription();
    }
}
