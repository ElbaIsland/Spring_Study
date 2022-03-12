package hello.core.member;

// 회원 서비스 인터페이스에 대한 구현체
public class MemberServiceImpl implements MemberService {

    // 회원 저장소 인터페이스, 우측 선언 통해 구현 객체 연결 so, 아래 명령어들이 제대로 동작하는...
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}