package kr.ac.primitive.controller;

import kr.ac.primitive.dto.PostDto;
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

    // 글 전체 불러오기
    @GetMapping
    public List<Post> showAll() {
        return postService.getAll();
    }

    /**
     * 특정 ID를 가진 Post를 반환하는 메서드입니다.
     * @param id 조회하고자 하는 Post의 ID
     * @return ID에 해당하는 Post 객체. 해당 ID를 가진 Post가 없을 경우 null 반환
     */
    @GetMapping("/{id}")
    public Post show(@PathVariable Long id){
        return postService.get(id);
    }

    //글 작성하기
    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostDto postDto){
        Post created = postService.createPost(postDto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //글 수정하기
    @PatchMapping("/{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody PostDto postDto){
        Post updated = postService.updatePost(id, postDto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //글 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<Post> delete(@PathVariable Long id){
        Post deleted = postService.deletePost(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
