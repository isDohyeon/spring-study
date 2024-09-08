package level1.discount;

import level1.member.Grade;
import level1.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP라면 10% 할인 적용")
    void vip_o() {
        // given
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        // when
        int discount = discountPolicy.discount(memberA, 10000);
        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니라면 할인 적용 x")
    void vip_x() {
        // given
        Member memberB = new Member(2L, "memberB", Grade.BASIC);
        // when
        int discount = discountPolicy.discount(memberB, 10000);
        // then
        assertThat(discount).isEqualTo(0);
    }
}