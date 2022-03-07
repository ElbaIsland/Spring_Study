package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// alt + enter로 위로 올림
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


// 굳이 어디다 가져다 쓸 필요가 없으므로 public X, 일반 class로 만들어줌
class MemoryMemberRepoTest {

    MemoryMemberRepo repo = new MemoryMemberRepo(); //  공통으로 쓸 repo

    @AfterEach  //  각 테스트 후 아래 명령어 실행
    public void afterEach(){
        repo.clearStore();  //  테스트가 끝날 때마다, 각 메소드별 개별 변수 세팅 등을 위해 전부 clear 시켜주어야 한다.
    }

    @Test
    public void save(){ // 좌측 초록버튼 클릭 후 실행시, 아래 run 결과가 테스트형식으로 출력된다.
        Member member = new Member();
        member.setMemberName("Spring-jUnit-test");
        repo.saveMember(member);

        Member testResult = repo.findMembById(member.getSystemId()).get();
        System.out.println("testResult = " + (testResult == member));   //  soutv 명령어 기억하자!

        // 1) junit Assertions : 성공시 초록불 / 실패시 빨간불로 확인 가능
        assertEquals(member, testResult);    //  crrl+p로 확인해보자구~

        // 2) assertj Assertionstions : 주로 사용하는 방법
        assertThat(member).isEqualTo(testResult);   // ** alt + enter로 편하게 
    }

    @Test
    public void findByName(){

        Member member1 = new Member();
        member1.setMemberName("spring-test1");
        repo.saveMember(member1);

        // shift + f6 : 개꿀팁 2 => 복붙시 에러나는 변수명에 대고 키 누르면 전체 변수명 변경가능
        Member member2 = new Member();
        member2.setMemberName("spring-test1");
        repo.saveMember(member2);

        Member result = repo.findMembByName("spring-test1").get();

        assertThat(result).isEqualTo(member1);
        assertThat(result).isEqualTo(member2);  //  여기서 에러발생 확인
    }

    @Test
    public void findAll(){
        //given
        Member member1 = new Member();
        member1.setMemberName("spring1");
        repo.saveMember(member1);
        Member member2 = new Member();
        member2.setMemberName("spring2");
        repo.saveMember(member2);
        //when
        List<Member> result = repo.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.size()).isEqualTo(3); //  이러면 에러나겠지
    }

}
