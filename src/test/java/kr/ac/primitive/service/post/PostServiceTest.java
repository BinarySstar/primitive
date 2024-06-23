package kr.ac.primitive.service.post;

import kr.ac.primitive.dto.post.request.PostRequestDto;
import kr.ac.primitive.dto.post.response.PostResponseDto;
import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.entity.post.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private List<Post> posts;

    private PostRequestDto postRequestDto;

    private final static int BOUND = 10;
    @BeforeEach
    void setUp() {
        posts = new ArrayList<>();
        for(int i = 1; i <= BOUND; i++) {
            posts.add(createPost(i));
        }

        postRequestDto = new PostRequestDto.Builder()
                .title("title1")
                .summary("summary1")
                .description("description1")
                .image("image1")
                .isPublic(true)
                .build();
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
    @DisplayName("모든 Post를 조회하면 리스트가 반환되어야 한다.")
    void getAllPosts() {
        // given
        when(postRepository.findAll()).thenReturn(posts);

        // when
        List<PostResponseDto> allPosts = postService.getAllPosts();

        // then
        assertNotNull(allPosts);
        assertEquals(BOUND, allPosts.size());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("랜덤으로 Post를 조회하면 리스트가 반환되어야 한다.")
    void getRandomPosts() {
        // given
        when(postRepository.findAll()).thenReturn(posts);

        // when
        List<PostResponseDto> randomPosts = postService.getRandomPosts();

        // then
        assertNotNull(randomPosts);
        assertTrue(randomPosts.size() <= 3);
        verify(postRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Post를 ID로 조회하면 Post가 반환되어야 한다.")
    void getPost() {
        // given
        Post post = posts.get(1);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));

        // when
        Post foundPost = postService.getPost(2L);

        // then
        assertNotNull(foundPost);
        assertEquals(post, foundPost);
        verify(postRepository, times(1)).findById(2L);
    }

    @Test
    @DisplayName("Post 생성한 후에는 생성된 Post가 반환되어야 한다.")
    void createPost() {
        // given
        Post post = posts.get(0);

        when(postRepository.save(any(Post.class))).thenReturn(post);
        // when
        Post createdPost = postService.createPost(postRequestDto);

        // then
        assertNotNull(createdPost);
        assertEquals(createdPost, post);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    @DisplayName("Post를 ID로 조회하여 수정하면 수정된 Post가 반환되어야 한다.")
    void updatePost() {
        // given
        Post post = posts.get(0);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        // when
        Post updatedPost = postService.updatePost(1L, postRequestDto);

        // then
        assertNotNull(updatedPost);
        assertEquals(updatedPost.getTitle(), postRequestDto.getTitle());
        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    @DisplayName("Post를 ID로 조회하여 삭제하면 삭제된 Post가 반환되어야 한다.")
    void deletePost() {
        // given
        Post post = posts.get(0);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        doNothing().when(postRepository).delete(any(Post.class));

        // when
        Post deletedPost = postService.deletePost(1L);

        // then
        assertNotNull(deletedPost);
        verify(postRepository, times(1)).findById(1L);
        verify(postRepository, times(1)).delete(any(Post.class));
    }
}