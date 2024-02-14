package kr.ac.primitive;

import jakarta.annotation.PostConstruct;
import kr.ac.primitive.user.User;
import kr.ac.primitive.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    @Autowired
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user = new User();
        user.setLoginId("test");
        user.setPassword("test!");
        user.setName("테스터");
        userRepository.save(user);
    }
}
