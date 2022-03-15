package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 공연 기획자와 같이, 애플리케이션의 전체 동작 방식을 구성(=설정)하는 클래스
// 기존에 impl(클라)에서 일일이 new 생성자로 처음 선언했던 구현 클래스 부분을, 아래의 방식으로 통일하여 정리
public class AppConfig {

    // 어디선가 AppConfig를 통해 memberService를 불러서 쓰게 되면 (=> new MemoryMemberRepository())
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(
                new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
