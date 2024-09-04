package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // 각 테스트 메서드가 실행되기 전에 실행되어 테스트 환경을 초기화
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    // 테스트 메서드가 실행된 후 실행되어 테스트 후 정리 작업 - 각 테스트 케이스 초기화
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    // 회원 가입 기능을 테스트
    // 회원가입 후 이름 일치 여부를 통해 회원 가입 검증
    @Test
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("dohyeon");

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

    @Test
    void findMembers() {
    }

    @Test
    void findByOne() {
    }
}