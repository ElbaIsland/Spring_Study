package hello.hellospring.controller;

import hello.hellospring.service.Component_MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 03.08 : 스프링 빈과 의존관계 설정

// 1) 컨트롤러 어노테이션을 통해, 스프링 실행시 컨트롤러 객제를 자동으로 보관하여 관리하게 된다
// 이를 스프링 컨테이너에서 스프링 빈이 관리된다고 표현한다.
// * 컨트롤러를 통해 외부 요청을 받고, 서비스에서 비즈니스 로직을 만들고, repo에서 데어터를 저장하는 정형화된 패틴 기억
@Controller
public class Component_MemberController {

    // 2) 스프링 컨테이너에 repository 단 하나 보관하고,
    private final Component_MemberService memberService;

    @Autowired // 아래 MemberService와 스프링 컨테이너의 MemberService를 연동시켜준다.
    // 이를 통해 컨트롤러 생성시 spring bean에 등록된 memberservice 객체를 가져다 넣어주는 것을 DI라 한다는 것 기억하기!
    public Component_MemberController(Component_MemberService memberService) {
        this.memberService = memberService;
    }


}
