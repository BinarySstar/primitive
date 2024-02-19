package kr.ac.primitive.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(null);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register() {
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/checkId")
    public ResponseEntity<?> checkId() {
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update() {
        return ResponseEntity.ok().body(null);
    }
}
