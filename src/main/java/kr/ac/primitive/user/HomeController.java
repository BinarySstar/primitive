package kr.ac.primitive.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.ac.primitive.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private final UserRepository userRepository;

    /*@GetMapping("/")
    public String home(){
        return "home";
    }*/

    /**
     * 스프링의 @SessionAttribute를 이용하여 세션을 찾고, 세션에 들어있는 데이터를 찾는 번거로운 과정을 한번에 편리하게 처리
     */
    @GetMapping("/")
    public String homeLogin (@SessionAttribute(name=SessionConst.LOGIN_USER, required = false) User loginUser, Model model) {
        //세션이 없으면 home
        if (loginUser == null) {
            return "home";
        }

        model.addAttribute("member", loginUser);
        return "loginHome";
    }
}
