package kr.ac.primitive.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Transactional
    public List<Post> showAll() {
        return postRepository.findAll();
    }

    @Transactional
    public Post show(Long id){
        log.info("Entity id : {} is called", id);
        return postRepository.findById(id).orElse(null);
    }

    @Transactional
    public Post createPost(PostDto postDto) {
        Post post = postDto.toEntity();
        if(post.getId() != null) {
            return null;
        }
        return postRepository.save(post);
    }

    @Transactional
    public Post updatePost(Long id, PostDto postDto){
        Post post = postDto.toEntity();
        Post target = postRepository.findById(id).orElse(null);
        if(target == null || id != post.getId()){
            log.info("target : {}, id : {}, post.getId() : {}", target, id, post.getId());
            return null;
        }
        target.update(post);
        log.info("Entity id : {} is updated", target.getId());
        return postRepository.save(target);
    }

    @Transactional
    public Post deletePost(Long id){
        Post target = postRepository.findById(id).orElse(null);
        if(target == null) {
            return null;
        }
        postRepository.delete(target);
        log.info("Entity id : {} is deleted", target.getId());
        return target;
    }
}
