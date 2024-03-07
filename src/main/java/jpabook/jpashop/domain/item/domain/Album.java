package jpabook.jpashop.domain.item.domain;

import jpabook.jpashop.domain.item.ItemForm;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
@Getter @Setter
public class Album extends Item {

    private String artist;
    private String etc;

    @Override
    public Item createItem(ItemForm itemForm) {
        this.setName(itemForm.getName());
        this.setPrice(itemForm.getPrice());
        this.setStockQuantity(itemForm.getStockQuantity());
        this.setArtist(itemForm.getArtist());
        this.setEtc(itemForm.getEtc());
        return this;
    }
}
