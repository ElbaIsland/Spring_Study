package hello.hellospring.domain;

// 3.3~ 회원(Member) 기본 도메인
public class Member {

    private Long systemId;
    private String memberName;

    public Long getSystemId() {
        return systemId;
    }

    public void setSystemId(Long systemId) {
        this.systemId = systemId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
