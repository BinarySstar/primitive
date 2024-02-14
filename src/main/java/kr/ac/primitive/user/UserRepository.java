package kr.ac.primitive.user;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Slf4j
public class UserRepository {
    private static ConcurrentHashMap<Long, User> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    public User save(User user) {
        user.setId(++sequence);
        log.info("저장 : user = {}", user);
        store.put(user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return store.get(id);
    }

    public Optional<User> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(user -> user.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
    public void clearStore() {
        store.clear();
    }

}
