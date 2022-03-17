package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
//      MemberService memberService = new MemberServiceImpl(null);
//      OrderService orderService = new OrderServiceImpl(null, null);

        /**03.15 의존성주입 잘 된 version
         AppConfig appConfig = new AppConfig();
         MemberService memberService = appConfig.memberService();
         OrderService orderService = appConfig.orderService();
         */

        /**03.17 (위 코드를) Spring 기반으로 변경
         1) 우측 선언문의 AppConfig.class를 통해, Spring이 AppConfig에서 @Bean으로 등록된 객체들을 컨테이너에 넣어서 관리할 수 있다.
         2) 컨테이너에 등록된 각각의 메소드는 이름을 통해 꺼내올 수 있다.(기본적으로 메소드명을 통해 객체가 스프링 컨테이너에 저장된다.)
         프로그램 실행시, 로그를 통해 Spring 컨테이너에 각각의 객체가 저장되는것 확인 가능하다!
         */
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 1)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// 2)
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);// 2)

        // 계정생성
        Long memberId = 1L;
        Member member = new Member(memberId, "MemberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "ItemA", 10000);

        System.out.println("order : " + order);

    }

}
