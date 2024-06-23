package kr.ac.primitive.entity.post;

import kr.ac.primitive.dto.post.request.PostRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    private List<Post> posts;
    private final Random random = new Random();

    @BeforeEach
    void setup() {
        posts = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            posts.add(createPost(i));
        }
        postRepository.saveAll(posts);
    }

    private Post createPost(int i) {
        return new Post.Builder()
                .title("title" + i)
                .summary("summary" + i)
                .description("description" + i)
                .image("image" + i)
                .isPublic(true)
                .build();
    }

    @Test
    @DisplayName("Post 저장 테스트")
    void savePost() {
        // given
        Post post = getRandomPost();

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertNotNull(savedPost);
        assertNotNull(savedPost.getId());
        assertEquals(post, savedPost);
    }

    @Test
    @DisplayName("모든 Post 조회")
    void findAllPosts() {
        // when
        List<Post> allPosts = postRepository.findAll();

        // then
        assertEquals(posts.size(), allPosts.size());
    }

    @Test
    @DisplayName("특정 Post 조회")
    void getPost() {
        // given
        Post post = getRandomPost();

        // when
        Post foundPost = postRepository.findById(post.getId()).orElse(null);

        // then
        assertNotNull(foundPost);
        assertEquals(post, foundPost);
    }

    @Test
    @DisplayName("Post 수정")
    void updatePost() {
        // given
        Post post = getRandomPost();
        postRepository.save(post);
        PostRequestDto updateRequest = new PostRequestDto.Builder()
                .title("updated title")
                .summary("updated summary")
                .description("updated description")
                .image("updated image")
                .isPublic(false)
                .build();

        // when
        post.update(updateRequest);
        Post updatedPost = postRepository.save(post);

        // then
        Post foundPost = postRepository.findById(post.getId()).orElse(null);
        assertNotNull(foundPost);
        assertEquals(updatedPost, foundPost);
    }

    @Test
    @DisplayName("Post 삭제")
    void deletePost() {
        // given
        Post post = getRandomPost();
        postRepository.save(post);

        // when
        postRepository.delete(post);

        // then
        Post foundPost = postRepository.findById(post.getId()).orElse(null);
        assertNull(foundPost, "삭제된 Post");
    }

    private Post getRandomPost() {
        return posts.get(random.nextInt(posts.size()));
    }
}