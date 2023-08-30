package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// 상속 관계
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 부모. 한 테이블에 모두 넣기
@DiscriminatorColumn(name = "dtype") // 상속 관계 맵핑
@Getter @Setter
public abstract class Item { // 추상체로 만듬

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직== // setter가 아닌 비즈니스 로직을 이용해서 수량 조절
    public void addStock(int quantity) { // 재고 수량 증가
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) { // 재고 줄이기
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
}
