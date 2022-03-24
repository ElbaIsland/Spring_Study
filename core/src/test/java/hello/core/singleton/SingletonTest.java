package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

// 03.23 : 웹 어플리케이션과 싱글톤
public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI(의존성 주입) 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체가 생성될 것
        // appconfig 내부 처리 로직에 따라, 이 테스트코드 내에 객체만 네개가 생성되었다...
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른것 확인 >> 객체가 각각 따로 생성되었다.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){

    //  SingletonService singletonService1 = new SingletonService();  << 에러 나는것 확인 : 더이상 따로 객체생성불가
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 참조값이 같은 확인 >> 객체가 각각 따로 생성되었다.
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // singletonService2 == singletonService2
        // isEqualTo : 자바에서 쓰이는 그 equls 메소드(값일치를 말하는 건가..?), isSameAs : 객체의 인스턴스가 같은지 판단
        Assertions.assertThat(singletonService2).isSameAs(singletonService2);
    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // 윗부분 pureContainer와 비교하면서 확인해보자!
        AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회 : 호출할 때 마다 객체가 생성될 것
        // appconfig 내부 처리 로직에 따라, 이 테스트코드 내에 객체만 네개가 생성되었다...
        MemberService memberService1 = acac.getBean("memberService", MemberService.class);
        MemberService memberService2 = acac.getBean("memberService", MemberService.class);

        // 참조값이 같은것 확인 >> 하나의 객체만 생성되어있는 상태!
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 == memberService2
        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }
}
