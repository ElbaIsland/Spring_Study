package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 03.09-10 추가 : 스프링 데이터 JPA
public interface SpringDataJpaMemberRepo extends JpaRepository<Member,Long>, MemberRepository{

    @Override
    Optional<Member> findByName(String name);
}
