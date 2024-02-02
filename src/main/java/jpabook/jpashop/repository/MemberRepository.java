package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByName(String name);
    /*
        //@PersistenceContext // 스프링이 엔티티 메니저 만들어줌
        private final EntityManager em;

        public void save(Member member) {
            em.persist(member); // 영속성 컨테스트에 member을 넣음
        }
        public Member findOne(Long id) { // 단건 조회
            return em.find(Member.class, id); // type, PK
        }
    // 회원 목록 뿌리기
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // from이 table이 아니라 엔티티임
                .getResultList();
    }

    public List<Member> findByName(String name) { // 특정 회원
        return em.createQuery("select m from Member m where m.name = :name", // 바인딩
                        Member.class)
                .setParameter("name", name)
                .getResultList();
    }
  */
}