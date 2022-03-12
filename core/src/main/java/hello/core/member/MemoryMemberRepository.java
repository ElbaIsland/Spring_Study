package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// 회원저장소 인터페이스에 대한 구현체
public class MemoryMemberRepository implements MemberRepository{

    // 데이터 넣을 저장소
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
