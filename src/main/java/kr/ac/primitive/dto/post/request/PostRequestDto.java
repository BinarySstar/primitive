package kr.ac.primitive.dto.post.request;

import kr.ac.primitive.entity.participant.Participant;
import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.entity.techstack.TechStack;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostRequestDto {
    private String title;
    private String summary;
    private String description;
    private String image;
    private boolean isPublic;
    private List<TechStack> techStacks;
    private List<Participant> participants;

    public PostRequestDto(String title, String summary, String description, String image, boolean isPublic, List<TechStack> techStacks, List<Participant> participants) {
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.image = image;
        this.isPublic = isPublic;
        this.techStacks = techStacks;
        this.participants = participants;
    }

    public Post toEntity() {
        return new Post(title, summary, description, isPublic, image, LocalDateTime.now(), null, techStacks, participants);
    }
}
