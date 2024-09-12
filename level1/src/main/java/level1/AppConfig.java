package level1;

import level1.discount.DiscountPolicy;
import level1.discount.FixDiscountPolicy;
import level1.discount.RateDiscountPolicy;
import level1.member.MemberRepository;
import level1.member.MemberService;
import level1.member.MemberServiceImp;
import level1.member.MemoryMemberRepository;
import level1.order.OrderService;
import level1.order.OrderServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImp(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImp(memberRepository(), getDiscountPolicy());
    }

    /**
     * MemberRepository는
     * 1. MemoryMemberRepository를 사용한다.
     * 2. DbMemberRepository를 사용한다.
     * @return 저장소의 종류
     */
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        // 1
        return new MemoryMemberRepository();
        // 2
        // return new DbMemberRepository();
    }

    /**
     * DiscountPolicy는
     * 1. FixDiscountPolicy를 사용한다.
     * 2. RateDiscountPolicy를 사용한다.
     * @return 할인 정책의 종류
     */
    @Bean
    public static DiscountPolicy getDiscountPolicy() {
        // 1
        // return new FixDiscountPolicy();
        // 2
        return new RateDiscountPolicy();
    }
}
