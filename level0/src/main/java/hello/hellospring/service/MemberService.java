package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemoryMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    // 데이터 저장소에 접근하는 멤버 변수
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    // 새로운 회원을 저장소에 등록
    // 만약 중복된 회원이라면 예외처리
    // 아니라면 회원 등록 후 저장된 회원 ID 반환
    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    // 저장소에 있는 모든 회원 반환
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 ID 로 특정 회원 조회
    // Optional 을 통해 조회된 회원이 없다면 Optional.empty() 반환
    public Optional<Member> findByOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
