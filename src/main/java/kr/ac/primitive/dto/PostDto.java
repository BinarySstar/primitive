package kr.ac.primitive.dto;

import kr.ac.primitive.entity.Post;

public class PostDto {
    private Long id;
    private String title;
    private String description;
    private String filename;
    private String filepath;

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .title(title)
                .description(description)
                .filename(filename)
                .filepath(filepath)
                .build();
    }
}
