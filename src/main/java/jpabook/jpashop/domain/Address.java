package jpabook.jpashop.domain;

import lombok.Getter;
import javax.persistence.Embeddable;

@Embeddable // JPA의 내장 타입으로 어디에나 내장되어 있을 수 있다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode; // 우편번호

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    protected Address(){
    }
}
