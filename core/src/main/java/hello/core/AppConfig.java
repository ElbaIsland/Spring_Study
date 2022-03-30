package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**  03.17 추가 : AppConfig Spring 기반으로 변경하기 By Annotation
 *   1) 먼저 클래스 앞에다가 Configuration(설정정보, 구성정보를 뜻함) 붙여주기
 *
 *   03.29 추가 : @Configuration과 싱글톤
 *   1. @Configuration은 싱글톤을 나타낸다?
 *
 */
@Configuration
public class  AppConfig {

    /**
     * AppConfig : 애플리케이션의 전체 동작 방식을 구성(=설정)하는 클래스 like (공연에서의) 공연 기획자.
     * 기존에 impl(클라이언트)에서 일일이 new 생성자로 처음 선언했던 구현 클래스 부분을, 아래의 방식으로 통일하여 정리한다!
     */

    // 어디선가 AppConfig를 통해 memberService를 불러서 쓰게 되면 (=> new MemoryMemberRepository())
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }

//    public OrderService orderService(){
//        return new OrderServiceImpl(
//                new MemoryMemberRepository(), new FixDiscountPolicy());
//    }

    // 03.16 추가(AppConfig 리펙토링) : 위 코드로도 DIP를 잘 지킬 수 있지만, 각 역할에 따른 구현을 한눈에 보고 이해할 수는 없다.
    // 이 역할을 잘 드러나도록 리펙토링이 필요하다!
    // 결론 : 아래와 같이 변경하면, 각 메소드별 역할을 명확하게 알 수 있다.

    /** 03.29 @Configuration과 싱글톤
     * 2. @Bean memberService = new MemoryMemberRepository() 형태
     *    @Bean orderService = new MemoryMemberRepository() 형태
     *    어? 싱글톤인데 왜 같은 메소드를 두번 호출하는거지?
     */


    /** 03.17 AppConfig Spring 기반으로 변경
     *  2) 각 메소드 앞에 @Bean(Spring Container를 뜻함) 붙여주기
     */
    @Bean
    public MemberService memberService(){
        // ==> ctrl + alt + m 단축키(리펙토링 메소드)로 new MemoryMemberRepository()를 변경 + 아래 메소드의 선언부는 interface를 가리키도록
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    // ==> ctrl + alt + m 단축키(리펙토링 메소드)로 new FixDiscountPolicy()를 변경 + 아래 메소드의 선언부는 interface를 가리키도록
    public OrderService orderService(){
        return new OrderServiceImpl(
                memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // 03.16 두번째 수정 : 만약 할인 정책에 수정이 들어간다면? (고정 => 비율정책으로...)
        // 아래와 같이 discountPolicy만 바꾸면 ok
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
