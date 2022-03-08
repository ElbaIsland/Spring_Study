package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepo;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 0308 스프링 빈과 의존관계 : Component 방식과 또 다른 방식 => 자바코드로 직접 스프링 빈 등록하는 방식
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());   //  service와 repository 연결
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepo();
    }
}
