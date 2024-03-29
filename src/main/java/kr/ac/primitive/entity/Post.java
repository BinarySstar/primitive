package kr.ac.primitive.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    // private String date;
    // private String[] participate;
    // private String[] techStack;

    public void update(Post post) {
        if(post.getTitle() != null)
            this.title = post.getTitle();
        if(post.getDescription() != null)
            this.description = post.getDescription();
    }
}
