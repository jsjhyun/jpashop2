package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    /*
    @PersistenceContext
    EntityManager em;

    public void save(Item item) { // 상품 저장
        if (item.getId() == null) { // id 존재 x = 상품 존재 x
            em.persist(item); // 저장
        } else {
            em.merge(item); // 있으면 update
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() { // 여러개 찾는거는 jql 사용
        return em.createQuery("select i from Item i",Item.class).getResultList(); // 파라미터 바인딩 - 결과 반환
    }
     */
}
