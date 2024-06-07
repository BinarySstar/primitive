package kr.ac.primitive.entity.participant;

import jakarta.persistence.*;
import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.entity.user.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Participant() {
    }

    public Participant(Post post, User user) {
        this.post = post;
        this.user = user;
    }
}
