package kr.ac.primitive.service.member;


import kr.ac.primitive.dto.member.request.MemberLogInRequestDto;
import kr.ac.primitive.dto.member.request.MemberRequestDto;
import kr.ac.primitive.dto.member.response.MemberResponseDto;
import kr.ac.primitive.entity.member.Member;
import kr.ac.primitive.entity.member.MemberRepository;
import kr.ac.primitive.entity.member.Role;
import kr.ac.primitive.exception.EmailAlreadyExistsException;
import kr.ac.primitive.exception.PasswordMismatchException;
import kr.ac.primitive.exception.StudentIdAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void 회원가입_성공() {
        // given
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .name("이진성")
                .email("example@email.co.kr")
                .studentId("202001234")
                .password("password")
                .checkPassword("password")
                .build();

        when(memberRepository.save(any(Member.class))).thenReturn(any(Member.class));

        // when
        MemberResponseDto result = memberService.register(requestDto);

        // then
        assertNotNull(result);
        assertEquals(result.getName(), "이진성");
        assertEquals(result.getEmail(), "example@email.co.kr");
        assertEquals(result.getStudentId(), "202001234");

        // verify
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    void 회원가입_실패_이메일_중복() {
        // given
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .name("이진성")
                .email("example123@email.com")
                .studentId("202101234")
                .password("password")
                .checkPassword("password")
                .build();

        when(memberRepository.existsByEmail(any(String.class))).thenReturn(true);

        // when
        Exception exception = assertThrows(EmailAlreadyExistsException.class, () -> memberService.register(requestDto));

        // then
        assertEquals(exception.getMessage(), "이미 존재하는 이메일입니다!");

        // verify
        verify(memberRepository, times(1)).existsByEmail(any(String.class));
        verify(memberRepository, times(0)).save(any(Member.class));
    }

    @Test
    void 회원가입_실패_학번_중복() {
        // given
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .name("이진성")
                .email("example@email.org")
                .studentId("202001234")
                .password("password")
                .checkPassword("password")
                .build();

        when(memberRepository.existsByStudentId(any(String.class))).thenReturn(true);

        // when
        Exception exception = assertThrows(StudentIdAlreadyExistsException.class, () -> memberService.register(requestDto));

        // then
        assertEquals(exception.getMessage(), "이미 존재하는 학번입니다!");

        // verify
        verify(memberRepository, times(1)).existsByStudentId("202001234");
        verify(memberRepository, times(0)).save(any(Member.class));
    }

    @Test
    void 회원가입_실패_비밀번호_불일치() {

        // given
        MemberRequestDto requestDto = MemberRequestDto.builder()
                .name("이진성")
                .email("example@email.ac.kr")
                .studentId("202011122")
                .password("password")
                .checkPassword("PAssword")
                .build();

        // when
        Exception exception = assertThrows(PasswordMismatchException.class, () -> memberService.register(requestDto));

        // then
        assertEquals(exception.getMessage(), "비밀번호가 일치하지 않습니다!");

        // verify
        verify(memberRepository, times(0)).save(any(Member.class));
    }

    @Test
    void 로그인_성공() {
        // given
        Member member = Member.builder()
                .name("이진성")
                .email("example@email.com")
                .studentId("202001234")
                .password("password")
                .role(Role.USER)
                .build();

        MemberLogInRequestDto requestDto = MemberLogInRequestDto.builder()
                .email("example@email.com")
                .password("password")
                .build();

        when(memberRepository.findByEmail(any(String.class))).thenReturn(Optional.of(member));
        // when(passwordEncoder.matches(any(String.class), any(String.class))).thenReturn(true);

        // when
        boolean isLogin = memberService.login(requestDto);

        // then
        assertTrue(isLogin);
    }

}