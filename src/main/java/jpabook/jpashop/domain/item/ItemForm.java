package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {
    private long id; //수정을 위한 id도 추가

    private String name;
    private int price;
    private int stockQuantity;

    private String dtype; //아이템 타입 (A, B, M)
    private String artist;
    private String etc;
    private String author;
    private String isbn;
    private String director;
    private String actor;
}