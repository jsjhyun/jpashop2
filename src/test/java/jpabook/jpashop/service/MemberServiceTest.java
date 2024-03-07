package jpabook.jpashop.service;

import jpabook.jpashop.domain.member.domain.Member;
import jpabook.jpashop.domain.member.service.MemberService;
import jpabook.jpashop.domain.member.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // junit을 spring과 같이 사용할래
@SpringBootTest
@Transactional // 롤백
public class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception { 
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(saveId));
    }

    @Test(expected = IllegalStateException.class) // try catch문 대신 사용
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");

        //When
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.

        //Then
        fail("예외가 발생해야 한다."); // 코드가 여기까지 오면 작동(예외 발생하면)
    }
}