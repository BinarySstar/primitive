package kr.ac.primitive.controller.post;

import kr.ac.primitive.dto.post.request.PostRequestDto;
import kr.ac.primitive.dto.post.response.PostResponseDto;
import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.service.post.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private MockMvc mockMvc;

    private Post post;
    private PostResponseDto responseDto1;
    private PostResponseDto responseDto2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();

        post = new Post.Builder()
                .title("title")
                .summary("summary")
                .description("description")
                .image("image")
                .isPublic(true)
                .build();

        responseDto1 = new PostResponseDto.Builder()
                .id(1L)
                .title("title")
                .summary("summary")
                .image("image")
                .createdAt(LocalDateTime.now())
                .build();

        responseDto2 = new PostResponseDto.Builder()
                .id(2L)
                .title("title2")
                .summary("summary2")
                .image("image2")
                .createdAt(LocalDateTime.now().plusDays(1))
                .build();
    }

    @Test
    @DisplayName("모든 Post를 조회하면 PostResponseDto 리스트를 반환한다.")
    void getAllPosts() throws Exception {

        // given
        List<PostResponseDto> posts = Arrays.asList(responseDto1, responseDto2);
        when(postService.getAllPosts()).thenReturn(posts);

        // when & then
        mockMvc.perform(get("/primitive/api/posts")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(posts.size()))
                .andExpect(jsonPath("$[0].id").value(responseDto1.getId()))
                .andExpect(jsonPath("$[0].title").value(responseDto1.getTitle()))
                .andExpect(jsonPath("$[0].summary").value(responseDto1.getSummary()))
                .andExpect(jsonPath("$[0].image").value(responseDto1.getImage()))
                .andExpect(jsonPath("$[0].createdAt").exists())
                .andExpect(jsonPath("$[1].id").value(responseDto2.getId()))
                .andExpect(jsonPath("$[1].title").value(responseDto2.getTitle()))
                .andExpect(jsonPath("$[1].summary").value(responseDto2.getSummary()))
                .andExpect(jsonPath("$[1].image").value(responseDto2.getImage()))
                .andExpect(jsonPath("$[1].createdAt").exists());

        verify(postService, times(1)).getAllPosts();
    }

    @Test
    @DisplayName("Id를 이용하여 Post를 조회하면 Post를 반환한다.")
    void getPost() throws Exception {

        // given
        when(postService.getPost(1L)).thenReturn(post);

        // when & then
        mockMvc.perform(get("/primitive/api/posts/1")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.summary").value(post.getSummary()))
                .andExpect(jsonPath("$.image").value(post.getImage()))
                .andExpect(jsonPath("$.createdAt").exists());

        verify(postService, times(1)).getPost(1L);
    }

    @Test
    @DisplayName("랜덤으로 Post를 조회하면 PostResponseDto 리스트를 반환한다.")
    void getRandomPosts() throws Exception {

        // given
        List<PostResponseDto> posts = Arrays.asList(responseDto1, responseDto2);
        when(postService.getRandomPosts()).thenReturn(posts);

        // when & then
        mockMvc.perform(get("/primitive/api/posts/random")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.length()").value(posts.size()))
                .andExpect(jsonPath("$[0].id").value(responseDto1.getId()))
                .andExpect(jsonPath("$[0].title").value(responseDto1.getTitle()))
                .andExpect(jsonPath("$[0].summary").value(responseDto1.getSummary()))
                .andExpect(jsonPath("$[0].image").value(responseDto1.getImage()))
                .andExpect(jsonPath("$[0].createdAt").exists())
                .andExpect(jsonPath("$[1].id").value(responseDto2.getId()))
                .andExpect(jsonPath("$[1].title").value(responseDto2.getTitle()))
                .andExpect(jsonPath("$[1].summary").value(responseDto2.getSummary()))
                .andExpect(jsonPath("$[1].image").value(responseDto2.getImage()))
                .andExpect(jsonPath("$[1].createdAt").exists());

        verify(postService, times(1)).getRandomPosts();
    }

    @Test
    @DisplayName("PostRequestDto를 이용하여 Post를 생성하면 Post를 반환한다.")
    void createPost() throws Exception {

        // given
        when(postService.createPost(any(PostRequestDto.class))).thenReturn(post);

        // when & then
        mockMvc.perform(post("/primitive/api/posts")
                .contentType("application/json")
                .content("{\"title\":\"title\",\"summary\":\"summary\",\"description\":\"description\",\"image\":\"image\",\"isPublic\": true}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.summary").value(post.getSummary()))
                .andExpect(jsonPath("$.image").value(post.getImage()))
                .andExpect(jsonPath("$.createdAt").exists());

    }

    @Test
    @DisplayName("Id와 PostRequestDto를 이용하여 Post를 수정하면 Post를 반환한다.")
    void updatePost() throws Exception{

        // given
        when(postService.updatePost(anyLong(), any(PostRequestDto.class))).thenReturn(post);

        // when & then
        mockMvc.perform(put("/primitive/api/posts/1")
                .contentType("application/json")
                .content("{\"title\":\"title\",\"summary\":\"summary\",\"description\":\"description\",\"image\":\"image\",\"isPublic\":true}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.summary").value(post.getSummary()))
                .andExpect(jsonPath("$.image").value(post.getImage()))
                .andExpect(jsonPath("$.createdAt").exists());
    }

    @Test
    @DisplayName("Id를 이용하여 Post를 삭제하면 삭제된 Post를 반환한다.")
    void deletePost() throws Exception {

        // given
        when(postService.deletePost(1L)).thenReturn(post);

        // when & then
        mockMvc.perform(delete("/primitive/api/posts/1")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(postService, times(1)).deletePost(1L);
    }
}