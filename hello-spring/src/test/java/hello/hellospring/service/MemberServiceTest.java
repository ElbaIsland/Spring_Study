package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

// *** test 쉽게 만드는 법!
// 테스트케이스 만들 클래스명에 마우스 오버 => ctrl + shift + t
class MemberServiceTest {



    //  1안. 사실 이건 좋은 클리어 방식이 아니다. 아래 Repo랑 실제 Service에서의 repo가 서로 다른 상황!
    //  ==> 2안으로 변경
//  MemberService memberService = new MemberService();
//  MemoryMemberRepo memberRepo = new MemoryMemberRepo();
    @AfterEach
    public void afterEach() {
        memberRepo.clearStore();
    }

    //  2안. 먼저 Memberservice 클래스의 MemberRepository 선언자를 빼버리고, 생성자를 만든다.
    //  다음으로, 아래와 같이 repo를 선언하고 @Before를 걸어 항상 Memberservice의 선언값과 연동되도록 한다!
    MemberService memberService;
    MemoryMemberRepo memberRepo;
    @BeforeEach
    public void beforeEach(){
        memberRepo = new MemoryMemberRepo();
        memberService = new MemberService(memberRepo);
    }

    // * 위와 같이, (memberService 입장에서) 직접 new 생성자로 부르는 것이 아니라 외부에서 repo를 넣어주는 식으로 코딩했다.
    // 이것이 스프링 핵심 원리 중 하나인 DI(Dependency Injection, 의존성 주입)다!

    @Test
    void 회원가입이지롱_테스트는_한글로해도되지롱() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveid = memberService.join(member);

        // then
        Member findedone = memberService.findOne(saveid).get();
        assertThat(member.getName()).isEqualTo(findedone.getName());    //  alt + enter Assertions 올린것 기억나제?
    }

    @Test
    void 중복회원_예외테스트() {
        //Given (이름중복)
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);

        // 방식 1
        try {
            memberService.join(member2);
            fail();
        }
        catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // success
       //   assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원이응이응."); // fail
        }

        // 방식 2
        IllegalStateException e
                = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findOne() {
    }
}