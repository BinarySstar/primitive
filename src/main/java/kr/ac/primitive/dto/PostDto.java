package kr.ac.primitive.dto;

import kr.ac.primitive.entity.Post;

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
