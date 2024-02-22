package kr.ac.primitive.ImageFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class ImageFileController {

    private final ImageFileService imageFileService;

    @Autowired
    public ImageFileController(ImageFileService imageFileService) {
        this.imageFileService = imageFileService;
    }

    // 이미지 파일 전체 불러오기
    @GetMapping("/image")
    public ResponseEntity<List<ImageFileDto>> showAll() {
        return null;
    }

    // 이미지 파일 불러오기
    @PostMapping
    public ResponseEntity<?> upload() {
        return null;
    }

    // 이미지 파일 수정
    @PatchMapping
    public ResponseEntity<?> update() {
        return null;
    }

    // 이미지 파일 삭제
    @DeleteMapping
    public ResponseEntity<?> delete() {
        return null;
    }
}
