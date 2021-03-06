package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 03.08 : 스프링 빈과 의존관계 설정

// 1) 컨트롤러 어노테이션을 통해, 스프링 실행시 컨트롤러 객제를 자동으로 보관하여 관리하게 된다
// 이를 스프링 컨테이너에서 스프링 빈이 관리된다고 표현한다.
// * 컨트롤러를 통해 외부 요청을 받고, 서비스에서 비즈니스 로직을 만들고, repo에서 데어터를 저장하는 정형화된 패틴 기억
@Controller
public class MemberController {

    // 2) 스프링 컨테이너에 repository 단 하나 보관하고,
    private final MemberService memberService;

    @Autowired // 아래 MemberService와 x`스프링 컨테이너의 MemberService를 연동시켜준다.
    // 이를 통해 컨트롤러 생성시 spring bean에 등록된 memberservice 객체를 가져다 넣어주는 것을 DI라 한다는 것 기억하기!
    // 하지만,,, 일반적인 Component 방식과 다르게 작업 들어가는 것 확인하자! (java 코드로 직접 bean 등록하기)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 03.09 추가 => basicHomePage 만들기
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 03.09 추가 => basicHomePage > 가입 페이지 만들기
    // 위에서 createMemberForm로 들어온 상태 >> method = post이므로, postmapping과 자동 연계된다.
    // get은 조회시, post는 데이터 조작시..기억나지?
    @PostMapping(value = "/members/new")
    public String create(BasicMemberForm form) {
        Member member = new Member();
        member.setName(form.getName());   //  BasicMemberForm의 이름을 set한다!!!
        memberService.join(member); //  new member join(회원가입) 로직
        return "redirect:/";
    }

    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findAllMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
