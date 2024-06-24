package kr.ac.primitive.entity.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 회원등록_성공() {
        //given
        Member member = Member.builder()
                .name("가나다")
                .email("example@email.com")
                .studentId("202001234")
                .password("password")
                .role(Role.USER)
                .build();

        //when
        Member result = memberRepository.save(member);

        //then
        assertNotNull(result.getId());
        assertEquals(result.getName(), "가나다");
        assertEquals(result.getEmail(), "example@email.com");
        assertEquals(result.getStudentId(), "202001234");
        assertEquals(result.getPassword(), "password");
        assertEquals(result.getRole(), Role.USER);
    }
}
