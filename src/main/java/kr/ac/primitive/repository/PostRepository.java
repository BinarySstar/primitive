package kr.ac.primitive.repository;

import kr.ac.primitive.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
