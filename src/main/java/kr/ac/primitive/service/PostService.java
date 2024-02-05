package kr.ac.primitive.service;

import kr.ac.primitive.dto.PostDto;
import kr.ac.primitive.entity.Post;
import kr.ac.primitive.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public List<Post> showAll() {
        return postRepository.findAll();
    }

    @Transactional
    public Post show(Long id){
        return postRepository.findById(id).orElse(null);
    }

    @Transactional
    public Post createPost(PostDto postDto) {
        return null;
    }

    @Transactional
    public Post updatePost(Long id, PostDto postDto){
        return null;
    }

    @Transactional
    public Post deletePost(Long id){
        // 1. 대상 찾기
        Post target = postRepository.findById(id).orElse(null);
        if(target == null) return null;
        // 2. 대상 삭제
        postRepository.delete(target);
        return target;
    }
}
