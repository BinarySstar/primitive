package kr.ac.primitive.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    // 파일 이름
    private String filename;

    // 파일 경로
    private String filepath;

    public void update(Post post) {
        if(post.getTitle() != null)
            this.title = post.getTitle();
        if(post.getDescription() != null)
            this.description = post.getDescription();
    }
}
