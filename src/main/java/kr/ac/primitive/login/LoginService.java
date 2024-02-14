package kr.ac.primitive.login;

import kr.ac.primitive.user.User;
import kr.ac.primitive.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    @Autowired
    private final UserRepository userRepository;

    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }

}
