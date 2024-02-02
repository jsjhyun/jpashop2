package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 조회(읽기)를 최적화
@RequiredArgsConstructor // 이 것 때문에
public class MemberService {

    // @PersistenceContext, @Autowired 생략 가능
    private final MemberRepository memberRepository;
    /**
     * 회원가입
     */
    // 쓰기는 readOnly X
    @Transactional // 우선 적용
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }


    // 회원 수정
    @Transactional
    public void update(Long id, String name){
        Member member = memberRepository.findById(id).orElse(null);
        member.setName(name);
    }




}