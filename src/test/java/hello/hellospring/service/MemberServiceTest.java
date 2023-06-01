package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memoryMemberRepository;
    MemberService memberService;

    @AfterEach
    public void afterEach() {
        // DI : 의존성 주입
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @Test
    void 회원가입() {
        // 1. given
        Member member = new Member();
        member.setName("코무지");

        // 2. when
        Long saveId = memberService.join(member);

        // 3. then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복회원에러() {
        // 1. given
        Member member = new Member();
        member.setName("코무지");

        Member member1 = new Member();
        member1.setName("코무지");

        // 2. when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
  /*      try{
            memberService.join(member1);
            fail();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }*/


    }

    @Test
    void 전체회원찾기() {
    }

    @Test
    void 회원찾기() {
    }
}