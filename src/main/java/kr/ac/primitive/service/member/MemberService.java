package kr.ac.primitive.service.member;

import kr.ac.primitive.dto.member.request.MemberLogInRequestDto;
import kr.ac.primitive.dto.member.request.MemberRequestDto;
import kr.ac.primitive.dto.member.response.MemberResponseDto;
import kr.ac.primitive.entity.member.Member;
import kr.ac.primitive.entity.member.MemberRepository;
import kr.ac.primitive.exception.EmailAlreadyExistsException;
import kr.ac.primitive.exception.EmailNotFoundException;
import kr.ac.primitive.exception.PasswordMismatchException;
import kr.ac.primitive.exception.StudentIdAlreadyExistsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberResponseDto register(MemberRequestDto requestDto) {
        validateRequest(requestDto);

        Member member = requestDto.toEntity();
        memberRepository.save(member);
        return MemberResponseDto.toDto(member);
    }

    private void validateRequest(MemberRequestDto requestDto) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(requestDto.getEmail())) {
            throw new EmailAlreadyExistsException("이미 존재하는 이메일입니다!");
        }

        // 학번 중복 체크
        if (memberRepository.existsByStudentId(requestDto.getStudentId())) {
            throw new StudentIdAlreadyExistsException("이미 존재하는 학번입니다!");
        }

        // 비밀번호 일치 여부 체크
        if (!requestDto.getPassword().equals(requestDto.getCheckPassword())) {
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다!");
        }
    }

    @Transactional
    public boolean login(MemberLogInRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("존재하지 않는 이메일입니다!"));

        return member.getPassword().equals(requestDto.getPassword());
    }
}
