package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 03.07 : 3.3~ 회원 repo 인터피에스 (현재 db x -> interface로 임시 구현 가정 상황)
public interface Component_MemberRepository {

    Member save(Member member);

    // * optional이란? return으로 가져오는 값이 null일때, Optional 기능을 통해 자동으로 처리해준다.
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    List<Member> findAll();

}
