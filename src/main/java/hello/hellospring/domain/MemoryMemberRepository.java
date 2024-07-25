package hello.hellospring.domain;

import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 회원 정보를 저장. Long 은 회원의 ID, Member 는 회원 객체
    private static Map<Long, Member> store = new HashMap();

    // 회원 ID를 생성할 때 사용
    private static long sequence = 0L;

    // 새로운 회원을 저장.
    // 회원 객체에 ID를 설정한 후, store 에 저장 후 회원 반환
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // ID로 회원을 조회
    // store 에서 ID로 회원을 찾아 반환. 존재하지 않는 경우 Optional.empty()를 반환
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // 이름으로 회원을 조회
    // store 의 모든 값을 스트림으로 변환하여 필터링. 이름이 일치하는 첫 번째 회원을 반환
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 모든 회원을 조회
    // store 에 저장된 모든 회원을 리스트로 반환
    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 저장소 비우기
    public void clearStore() {
        store.clear();
    }
}
