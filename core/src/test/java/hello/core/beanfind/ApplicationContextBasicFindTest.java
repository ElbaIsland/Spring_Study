package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;    //  기본적인 테스트
import static org.junit.jupiter.api.Assertions.assertThrows;

// 03.19 : 스프링 빈 조회 - 기본
public class ApplicationContextBasicFindTest {

    // 1) 스프링 컨테이너 생성
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회하기")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());   // 빈 명칭까지 정확히 보여진다.

        // 단축키 1) alt + enter에서 ondemand 선택해 import static org.assertj.core.api.Assertions.* => 이렇게 올리기도 가능하다.
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회하기")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("(인터페이스 말고) 구체 타입으로 조회하기")
    void findBeanByName2(){
        /**
         * 위에서는 MemberService인 인터페이스, 여기서는 MemberServiceImpl인 구체 클래스로 값 가져오기
         * spring bean 등록시, 각 인터페이스의 반환 타입(인스턴스 타입)도 등록이 된다는거..
         * 물론 이 코드를 자주 쓰는것은 좋지 않다. 역할과 구현을 구분하여 역할에 의존해야 하는데 구현에 의존한...
         */
        MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
        // 1번 : NoSuchBeanDefinitionException 오류발생
//        MemberService noNameError = ac.getBean("noNameError", MemberService.class);

        // 2번 : 아래 명명한 오류가 발생하면 테스트성공, 발생하지 않으면 테스트실패
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("noNameError", MemberService.class));
    }

}
