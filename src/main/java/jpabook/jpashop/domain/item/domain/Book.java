package jpabook.jpashop.domain.item.domain;

import jpabook.jpashop.domain.item.ItemForm;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("B") // 싱글 테이블로 저장될 때 구분은 되어야 함
@Getter @Setter
public class Book extends Item {

    private String author;
    private String isbn;

    @Override
    public Item createItem(ItemForm itemForm) {
        super.setName(itemForm.getName());
        super.setPrice(itemForm.getPrice());
        super.setStockQuantity(itemForm.getStockQuantity());
        this.isbn = itemForm.getIsbn();
        this.author = itemForm.getAuthor();
        return this;
    }

}
