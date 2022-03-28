package hello.core.member;

/*회원서비스 - MemberServieImpl , 즉 클라이언트가 이용하는 서비스*/
public class MemberServiceImpl implements  MemberService{

    private final MemberRepository memberRepository ;

    /*생성자를 통해서 MemberRepository에 뭐가 들어갈지 지정해줄 것이다*/
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
}
