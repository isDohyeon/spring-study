package level1.discount;

import level1.member.Member;

public interface DiscountPolicy {

    /**
     *
     * @return 할인 대상 금액
     */
    int discount(Member member, int itemPrice);
}
