package kr.ac.primitive.entity.post;

import kr.ac.primitive.dto.post.request.PostRequestDto;
import kr.ac.primitive.entity.user.Role;
import kr.ac.primitive.entity.user.User;
import kr.ac.primitive.entity.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Post가 DB에 저장되는지 테스트")
    void savePost() {
        // given
        User user = new User("name", "studentId", "password", Role.USER);
        userRepository.save(user);

        Post post = new Post.Builder()
                .title("title")
                .summary("summary")
                .description("description")
                .isPublic(true)
                .user(user)
                .build();

        // when
        Post savedPost = postRepository.save(post);

        // then
        assertEquals(post, savedPost);

        Post foundPost = postRepository.findById(savedPost.getId()).orElse(null);
        assertNotNull(foundPost);
        assertEquals(post, foundPost);
        assertEquals(savedPost, foundPost);
    }

    @Test
    @DisplayName("Post 여러 개를 한 번에 조회")
    void getAllPost() {
        //given
        User user = new User("name", "studentId", "password", Role.USER);
        userRepository.save(user);

        Post post1 = new Post.Builder()
                .title("title1")
                .summary("summary1")
                .description("description1")
                .isPublic(true)
                .user(user)
                .techStacks(null)
                .build();

        Post post2 = new Post.Builder()
                .title("title2")
                .summary("summary2")
                .description("description2")
                .isPublic(true)
                .user(user)
                .image("image")
                .build();

        Post post3 = new Post.Builder()
                .title("title3")
                .summary("summary3")
                .description("description3")
                .isPublic(true)
                .user(user)
                .participants(null)
                .build();

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        //when
        List<Post> posts = postRepository.findAll();

        //then
        assertEquals(3, posts.size());
    }
    @Test
    @DisplayName("Post가 DB에서 삭제되는지 테스트")
    void deletePost(){
        // given
        User user = new User("name", "studentId", "password", Role.USER);
        userRepository.save(user);

        Post post = new Post.Builder()
                .title("title")
                .summary("summary")
                .description("description")
                .isPublic(true)
                .user(user)
                .build();

        Post savedPost = postRepository.save(post);

        // when
        postRepository.delete(savedPost);

        // then
        Post foundPost = postRepository.findById(savedPost.getId()).orElse(null);
        assertNull(foundPost);
    }

    @Test
    @DisplayName("PostReqeustDto를 통해 Post가 수정되는지 테스트")
    void updatePost() {
        // given
        User user = new User("name", "studentId", "password", Role.USER);
        userRepository.save(user);

        Post post = new Post.Builder()
                .title("title")
                .summary("summary")
                .description("description")
                .isPublic(true)
                .user(user)
                .build();

        Post savedPost = postRepository.save(post);

        // when
        // 저장된 post를 불러오고 PostReqeustDto를 통해 수정
        PostRequestDto postRequestDto = new PostRequestDto("new title", "new summary", "new description", "new image", false, null, null);
        Post updatedPost = postRepository.findById(savedPost.getId()).orElse(null);
        updatedPost.update(postRequestDto);

        // then
        assertNotNull(postRequestDto);
        assertEquals("new title", updatedPost.getTitle());
        assertEquals("new summary", updatedPost.getSummary());
        assertEquals("new description", updatedPost.getDescription());
        assertEquals("new image", updatedPost.getImage());
        assertFalse(updatedPost.isPublic());
        assertNull(updatedPost.getTechStacks());
        assertNull(updatedPost.getParticipants());
    }
}