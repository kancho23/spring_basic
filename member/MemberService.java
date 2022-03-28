package hello.core.member;

public interface MemberService {
    /*회원가입 회원조회 두가지 기능만 넣을것*/
    void join(Member member);

    Member findMember(Long memberId);
}
