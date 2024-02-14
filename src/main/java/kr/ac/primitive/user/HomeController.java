package kr.ac.primitive.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final UserRepository userRepository;

    /*@GetMapping("/")
    public String home(){
        return "home";
    }*/

    @GetMapping("/")
    public String homeLogin(
            @CookieValue(name = "memberId", required = false) Long memberId,
            Model model) {
        if (memberId == null) {
            return "home";
        }
        //로그인
        User loginUser = userRepository.findById(memberId);
        if (loginUser == null) {
            return "home";
        }
        model.addAttribute("member", loginUser);
        return "loginHome";
    }
}
