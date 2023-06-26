package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 생성자 생성
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void Join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(long memberId) {
        return memberRepository.findById(memberId);
    }
}
