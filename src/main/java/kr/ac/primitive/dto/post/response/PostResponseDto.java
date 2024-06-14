package kr.ac.primitive.dto.post.response;

import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.entity.techstack.TechStack;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String summary;
    private String image;
    private LocalDateTime createdAt;
    private List<TechStack> techStacks;

    public PostResponseDto(Long id, String title, String summary, String image, LocalDateTime createdAt, List<TechStack> techStacks) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.image = image;
        this.createdAt = createdAt;
        this.techStacks = techStacks;
    }
    public static PostResponseDto toDto(Post post) {
        return new PostResponseDto(post.getId(), post.getTitle(), post.getSummary(), post.getImage(), post.getCreatedAt(), post.getTechStacks());
    }
}
