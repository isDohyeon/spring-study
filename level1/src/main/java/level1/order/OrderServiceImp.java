package level1.order;

import level1.discount.DiscountPolicy;
import level1.discount.FixDiscountPolicy;
import level1.member.Member;
import level1.member.MemberRepository;
import level1.member.MemoryMemberRepository;

public class OrderServiceImp implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
