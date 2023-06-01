package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 스프링 컨테이너가 컨트롤러 어노테이션 된 컨트롤러 객체를 생성 해서 넣어둠 > 스프링이 관리하게됨

@Controller
public class MemberController {

    // 이렇게 쓰면 MemberService()가 별기능도 없는데 호출할때마다 객체를 생성하니 비효율적임
//    private final MemberService memberService = new MemberService();

    // 스프링 컨테이너에 등록하면 1개만 등록됨
    private final MemberService memberService;

    @Autowired  // Autowired : 인자 변수를 스프링컨테이너에있는 객체랑 연결시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);

        return "members/memberList";
    }
}
