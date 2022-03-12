package hello.core.member;

// 회원저장소 인터페이스
public interface MemberRepository {
    void save(Member member);
    Member findById(Long MemberId);
}
