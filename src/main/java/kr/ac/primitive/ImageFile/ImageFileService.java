package kr.ac.primitive.ImageFile;

import kr.ac.primitive.post.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ImageFileService {

    private final ImageFileRepository imageFileRepository;
    private final PostRepository postRepository;

    @Autowired
    public ImageFileService(ImageFileRepository imageFileRepository, PostRepository postRepository) {
        this.imageFileRepository = imageFileRepository;
        this.postRepository = postRepository;
    }

    public List<ImageFile> showAll() {
        return imageFileRepository.findAll();
    }
}
