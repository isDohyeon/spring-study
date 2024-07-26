package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    // 회원 가입 기능을 테스트
    // 회원가입 후 이름 일치 여부를 통해 회원 가입 검증
    @Test
    @Commit
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("dohyeon2");

        // When
        Long savedId = memberService.join(member);

        // Then
        Member findMember = memberRepository.findById(savedId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    // 중복 회원 가입 시 예외 발생 테스트
    // 첫 번째 회원을 가입시키고, 두 번째 회원을 가입시킬 때 예외가 발생하는지 확인
    // 예외 메시지가 "이미 존재하는 회원입니다."와 일치하는지 확인
    @Test
    public void 중복_회원_예외() {
        // Given
        Member member1 = new Member();
        member1.setName("dohyeon");

        Member member2 = new Member();
        member2.setName("dohyeon");

        // When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

}