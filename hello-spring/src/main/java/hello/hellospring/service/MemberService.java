package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// 03.07 회원 관리 예제 - 비즈니스 개발
// 03.09-10 추가 : jpa용 어노테이션 @Transactional
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return sysid
     */
    public Long join(Member member){

        // 1) 같은 이름 회원 가입 불가 룰
        // *** ctrl + alt + v 기억하자 => 자동 변수 추출
        // 방식 1
//        Optional<Member> membByName = memberRepository.findByName(member.getName());
//        membByName.ifPresent( member1 -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // 방식 2
        // 03-10 추가 : 상위부서 ORDER : 메소드별 작동 시간이 필요해 데이터를 넣어 확인해야 하는 상황이라면?
        // => 1) 아래 start, finish, timeMs를 다 메소드별로 꽃아 넣으면 너무 오래 걸리는데
        //  + 2) 시간측정은 핵심기능(ex- memberRepository.save)만 하면 되는데...좋은방법이 없을까?
        // ####### ===> 여기서 AOP가 등장한다!!! #######
        // long start = System.currentTimeMillis();

        try {
            DuplicateMemberChk(member); //  로직 만든 후 긁어서 cntl + t => extract method -> 메소드 만들기 가능
            memberRepository.save(member);
            return member.getId();
        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("timeMs : " + timeMs);
        }
    }

    /**
     * 전체 회원 조회
     * @param member
     * @return sysid
     */
    public List<Member> findAllMembers(){
        return memberRepository.findAll();
    }

    /**
     * id로 회원 조회
     * @param memberid
     * @return Member
     */
    public Optional<Member> findOne(Long memberid){
        return memberRepository.findById(memberid);
    }


    private void DuplicateMemberChk(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent( member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
