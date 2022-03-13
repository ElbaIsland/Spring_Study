package hello.core.member;

// 회원 서비스 인터페이스에 대한 구현체
public class MemberServiceImpl implements MemberService {

    // 회원 저장소 인터페이스, 우측 선언 통해 구현 객체 연결 so, 아래 명령어들이 제대로 동작하는...
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 03.13 추가 : 위 생성자 선언이 DI(DIP)를 잘 지키고 있는가? X => 뒷부분 선언이 다른 구현체로 되어 있다.
    // 즉, 의존관계가 MemberService라는 추상 & MemoryMemberRepository라는 구현 두 객체를 같이 의존하는 이상한 상태이다

    public void join(Member member) {
        memberRepository.save(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}