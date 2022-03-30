package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


/** 03.29-30 @Configuration과 싱글톤, @Configuration과 바이트 조작의 마법
 * Appconfig에서 MemoryMemberRepository를 두번 부르는 상황에서도, 싱글톤이 지켜지는지 확인
 */
public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 앞단의 impl에 만들어놨던 get 메소드를 열어 싱글톤 여부를 확인할 것.(테스트기 때문에 구체 클래스에서 꺼낸다..)
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);

        // 이번엔 구체클래스 x, 진짜 memberRepository 확인
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

        /** 각각 세번의 new MemoryMemberRepository를 불러오는 것 같은데, 왜 다 같은 객체가 호출될까? */

    }


    /** 03.30 @Configuration과 바이트 조작의 마법
     *
     */
    @Test
    void configurationDeepTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // Bean으로 등록된 AppConfig 자체를 가져와서 확인해볼 것
        AppConfig configBean = ac.getBean(AppConfig.class);
        System.out.println("configBean = " + configBean.getClass());
        /** print된 class 중간의 EnhancerBySpringCGLIB가 뭐지? */

    }

}
