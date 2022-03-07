package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

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
//        Optional<Member> membByName = memberRepository.findMembByName(member.getMemberName());
//        membByName.ifPresent( member1 -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // 방식 2
        DuplicateMemberChk(member); //  로직 만든 후 긁어서 cntl + t => extract method -> 메소드 만들기 가능

        memberRepository.saveMember(member);
        return member.getSystemId();
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
     * @param memberId
     * @return Member
     */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findMembById(memberId);
    }


    private void DuplicateMemberChk(Member member) {
        memberRepository.findMembByName(member.getMemberName())
                .ifPresent( member1 -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
