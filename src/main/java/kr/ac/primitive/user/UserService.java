package kr.ac.primitive.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public boolean login(Long id, String studentNumber, String password) {
        log.info("id = {}, 사용자 아이디 = {}, 비밀번호 = {}", id, studentNumber, password);
        Optional<User> userOptional = userRepository.findByStudentNumber(studentNumber);
        return userOptional
                .filter(user -> password.equals(user.getPassword()))
                .isPresent();
    }

    @Transactional
    public User register(UserDto userDto) {
        User user = userDto.toEntity();
        if(user.getId() != null) {
            return null;
        }
        return userRepository.save(user);
    }

    @Transactional
    public boolean checkId(String studentNumber) {
        log.info("입력받은 아이디 = {}", studentNumber);
        Optional<User> userOptional = userRepository.findByStudentNumber(studentNumber);
        return userOptional.isPresent();
    }

    @Transactional
    public User update(Long id, UserDto userDto) {
        User user = userDto.toEntity();
        User target = userRepository.findById(id).orElse(null);
        if(target == null || id != user.getId()) {
            log.info("target : {}, id : {}, user.getId() : {}", target, id, user.getId());
            return null;
        }
        target.update(user);
        log.info("Entity id : {} is updated", target.getId());
        return userRepository.save(user);
    }
}
