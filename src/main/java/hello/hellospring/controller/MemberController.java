package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 조회 - Get
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 실제 회원 등록 - Post
    // /members/new URL 로 들어오는 POST 요청을 이 메서드가 처리하도록 함
    // createMemberForm 의 input 에 있는 name 이 넘어옴
    // -> input 값으로 입력한 이름이 MemberForm 의 setName 을 통해 name 에 저장됨
    // 해당 name 으로 회원가입 진행됨
    // 메인 화면으로 리다이렉트 시킴
    @PostMapping(value = "members/new")
    public String create(MemberForm form) { // 폼 데이터가 바인딩되는 객체. 폼 필드의 name 속성 값이 MemberForm 객체의 name 필드에 매핑.
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    // members 로 오는 Get 요청에 모든 members 담기
    @GetMapping(value = "/members")
    public String form(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
