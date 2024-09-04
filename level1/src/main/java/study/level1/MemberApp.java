package study.level1;

import study.level1.member.Grade;
import study.level1.member.Member;
import study.level1.member.MemberService;
import study.level1.member.MemberServiceImp;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImp();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getId());
        System.out.println("findMember = " + findMember.getId());
    }
}
