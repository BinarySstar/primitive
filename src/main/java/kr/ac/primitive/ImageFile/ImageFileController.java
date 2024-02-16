package kr.ac.primitive.ImageFile;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/image")
public class ImageFileController {

    private final ImageFileService imageFileService;

    @PostMapping
    public ResponseEntity<> upload() {}

    @PatchMapping
    public ResponseEntity<> update() {}

    @DeleteMapping
    public ResponseEntity<> delete() {}
}
