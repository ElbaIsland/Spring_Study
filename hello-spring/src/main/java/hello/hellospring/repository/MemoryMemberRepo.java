package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// 위에서 구현한  Member, MemberRepo 

public class MemoryMemberRepo implements MemberRepository {

    private static Map<Long, Member> storeMembRepo = new HashMap<>();
    private static Long seq = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++seq);  //  id는 코드값으로 1씩 증가해서 세팅하기
        storeMembRepo.put(member.getId(), member);    //  코드값과 데이터(member)는 store map에다 넣어주기
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(storeMembRepo.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return storeMembRepo.values().stream() //  stream 람다식 >> loof를 돌린다.
                .filter(member -> member.getName().equals(name))
                .findAny(); //  member.getName이 메소드에서 파라미터로 넘어온 name과 같은지 확인, 하나라도 같으면 return
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(storeMembRepo.values());
    }

    public void clearStore(){
        storeMembRepo.clear();
    }

}
