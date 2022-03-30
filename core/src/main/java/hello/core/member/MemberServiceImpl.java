package hello.core.member;

// 회원 서비스 인터페이스에 대한 구현체
public class MemberServiceImpl implements MemberService {

    // 회원 저장소 인터페이스, 우측 선언 통해 구현 객체 연결, so 아래 명령어들이 제대로 동작된다.
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 03.13 추가 : 위 생성자 선언이 DI(DIP)를 잘 지키고 있는가? X => 뒷부분 선언이 다른 구현체로 되어 있다.
    // 즉, 의존관계가 MemberService라는 추상 & MemoryMemberRepository라는 구현 두 객체를 같이 의존하는 이상한 상태이다

    private final MemberRepository memberRepository; // 03.15 수정 => AppConfig 추가로 위와 같은 잘못된 방식 개선

    /*
     AppConfig를 통해 호출된 memberService가 MemberServiceImpl(new MemoryMemberRepository())를 불러온다.
     => 그리고 좌측 this.에 붙어, 이 impl 구현체 memberRepository 객체에 Appconfig 값이 들어간다!
     => 이를 전문용어로 '생성자 주입'이라고 한다.
    */
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /** 03.29 @Configuration과 싱글톤
     *  싱글톤 여부를 확인하기 위한 테스트 용도
     */
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}