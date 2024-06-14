package kr.ac.primitive.service;

import kr.ac.primitive.dto.post.request.PostRequestDto;
import kr.ac.primitive.dto.post.response.PostResponseDto;
import kr.ac.primitive.entity.post.Post;
import kr.ac.primitive.entity.post.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostResponseDto::toDto).toList();
    }

    @Transactional(readOnly = true)
    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Transactional
    public Post createPost(PostRequestDto requestDto) {
        Post post = requestDto.toEntity();
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.update(requestDto);
            postRepository.save(post);
        }
        return post;
    }

    @Transactional
    public Post deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            postRepository.delete(post);
        }
        return post;
    }
}
