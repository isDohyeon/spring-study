package study.level1.discount;

import study.level1.member.Member;

public interface DiscountPolicy {

    /**
     *
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
