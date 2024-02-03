package jpabook.jpashop.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("M")
@Getter // @Setter
public class Movie extends Item {

    private String director;
    private String actor;

    @Override
    public Item createItem(ItemForm itemForm) {
        super.setName(itemForm.getName());
        super.setPrice(itemForm.getPrice());
        super.setStockQuantity(itemForm.getStockQuantity());
        this.director = itemForm.getDirector();
        this.actor = itemForm.getActor();
        return this;
    }
}
