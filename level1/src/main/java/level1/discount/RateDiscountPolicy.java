package level1.discount;

import level1.member.Grade;
import level1.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {
    /**
     * 할인 금액을 정하고, 멤버의 등급에 따라 할인 금액을 반환한다.
     */

    private static final int discountPercent = 10;

    @Override
    public int discount(Member member, int itemPrice) {
        if (member.getGrade() == Grade.VIP) {
            return itemPrice * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
