package kr.ac.primitive.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    @GetMapping("/add")
    public String addForm(User user) {
        return "users/addUserForm";
    }

    @PostMapping("/add")
    public String save(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "users/addUserForm";
        }
        userRepository.save(user);
        return "redirect:/";
    }

}
