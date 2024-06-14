package kr.ac.primitive.controller.post;

import kr.ac.primitive.dto.post.request.PostRequestDto;
import kr.ac.primitive.dto.post.response.PostResponseDto;
import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/primitive/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id){
        Post post = postService.getPost(id);
        return (post != null) ?
                ResponseEntity.status(HttpStatus.OK).body(post) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //글 작성하기
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequestDto requestDto){
        Post created = postService.createPost(requestDto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //글 수정하기
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        Post updated = postService.updatePost(id, requestDto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //글 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id){
        Post deleted = postService.deletePost(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
