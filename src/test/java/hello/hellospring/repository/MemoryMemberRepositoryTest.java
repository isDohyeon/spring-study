package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.domain.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트케이스 실행 후 리포지토리 초기화
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        // 테스트 통과
        Member member1 = new Member();
        member1.setName("dohyeon1");
        repository.save(member1);

        // 에러
        // org.opentest4j.AssertionFailedError:
        // expected: hello.hellospring.domain.Member@778d1062
        // but was: hello.hellospring.domain.Member@2b6faea6
        // Expected :hello.hellospring.domain.Member@778d1062
        // Actual   :hello.hellospring.domain.Member@2b6faea6
        Member member2 = new Member();
        member2.setName("dohyeon2");
        repository.save(member2);

        Member result = repository.findByName("dohyeon1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void finaAll() {
        Member member1 = new Member();
        member1.setName("dohyeon1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("dohyeon1");
        repository.save(member2);

        List<Member> result = repository.findAll();

        // isEqualTo(2) : 통과
        // isEqualTo(3) : 에러
        // org.opentest4j.AssertionFailedError:
        // expected: 3
        // but was: 2
        // Expected :3
        // Actual   :2
        assertThat(result.size()).isEqualTo(2);
    }
}
