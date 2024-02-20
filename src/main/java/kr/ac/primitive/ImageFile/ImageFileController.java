package kr.ac.primitive.ImageFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/image")
public class ImageFileController {

    private final ImageFileService imageFileService;

    @Autowired
    public ImageFileController(ImageFileService imageFileService) {
        this.imageFileService = imageFileService;
    }

    @PostMapping
    public ResponseEntity<?> upload() {
        return null;
    }

    @PatchMapping
    public ResponseEntity<?> update() {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        return null;
    }
}
