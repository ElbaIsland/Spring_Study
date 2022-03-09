package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 0308 스프링 빈과 의존관계 : Component 방식과 또 다른 방식 => 자바코드로 직접 스프링 빈 등록하는 방식
@Configuration
public class SpringConfig {

    // 스프링 데이터 JPA 위해 지운 상태
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // 03.09-10 추가 : 스프링 데이터 JPA
    private final MemberRepository memberRepository;
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
    public MemberService memberService() {
        // 03.09-10 >> 스프링 데이터 JPA위해 주석처리
//      return new MemberService(memberRepository());   //  service와 repository 연결
        return new MemberService(memberRepository); // 위에서 선언한 memberRepository와 연계
    }

    // 03.09 추가 : 기본 jpa
    // 03.10 스프링 데이터 JPA위해 주석처리
//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepo(); << 구형 테스트 로직
//        return new JpaMemberRepository(em); <<
//    }

    // 03.10 2-1) TimeTraceAop 빈에 추가 or...
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }

}
