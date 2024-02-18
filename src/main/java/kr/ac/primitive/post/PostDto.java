package kr.ac.primitive.post;

import lombok.Getter;
import lombok.Setter;

@Getter
public class PostDto {
    private Long id;
    private String title;
    private String description;

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }
}
