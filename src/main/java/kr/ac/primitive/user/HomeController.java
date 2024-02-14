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
    public String homeLogin (HttpServletRequest request, Model model) {
        //세션이 없으면 home
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }
        //로그인
        User loginUser = (User)session.getAttribute(SessionConst.LOGIN_USER);
        if (loginUser == null) {
            return "home";
        }

        model.addAttribute("member", loginUser);
        return "loginHome";
    }
}
