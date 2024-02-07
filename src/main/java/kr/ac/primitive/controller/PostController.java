package kr.ac.primitive.controller;

import kr.ac.primitive.dto.PostDto;
import kr.ac.primitive.entity.Post;
import kr.ac.primitive.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService postService;

    // 글 전체 불러오기
    @GetMapping
    public List<Post> showAll() {
        return postService.showAll();
    }

    // 글 불러오기
    @GetMapping("/{id}")
    public Post show(@PathVariable Long id){
        return postService.show(id);
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
