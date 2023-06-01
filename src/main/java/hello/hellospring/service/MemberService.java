package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service    // 스프링 컨테이너가 읽을수 있게 알려줌다
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {

        validateDuplicateMember(member);    // 중복회원X
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // 안예뼈서 아래와 같이 쓰는것을 추천

/*        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });*/

        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 아이디로 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
