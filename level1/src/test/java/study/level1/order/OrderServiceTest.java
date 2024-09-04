package study.level1.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import study.level1.member.Grade;
import study.level1.member.Member;
import study.level1.member.MemberService;
import study.level1.member.MemberServiceImp;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService = new MemberServiceImp();
    OrderService orderService = new OrderServiceImp();

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}