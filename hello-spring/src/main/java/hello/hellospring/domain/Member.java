package hello.hellospring.domain;


import javax.persistence.*;

// 3.3~ 회원(Member) 기본 도메인
@Entity // 03.09-10 추가 : jpa 추가 방법
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db 컬럼별 고유 id인 IDENTITY(seq 느낌)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

}
