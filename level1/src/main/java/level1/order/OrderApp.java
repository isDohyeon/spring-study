package level1.order;

import level1.member.Grade;
import level1.member.Member;
import level1.member.MemberService;
import level1.member.MemberServiceImp;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImp();
        OrderService orderService = new OrderServiceImp();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order : " + order);
    }
}
