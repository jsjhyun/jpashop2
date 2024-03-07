package jpabook.jpashop.domain.delivery.domain;

import jpabook.jpashop.address.Address;
import jpabook.jpashop.domain.Enum.DeliveryStatus;
import jpabook.jpashop.domain.order.domain.Order;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // 꼭 string...ORDINAL은 숫자 // 배송 상태
    private DeliveryStatus status; // READY, COMP
}
