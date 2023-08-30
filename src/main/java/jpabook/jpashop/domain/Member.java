package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter // lombok 사용해서 따로 작성 안해도 됨
public class Member {

    @Id @GeneratedValue // db가 자동으로 생성
    @Column(name = "member_id") // 기본키 테이블명
    private Long id;
    private String name;

    @Embedded // @Embeddable과 둘 중 하나만 있어도 됨
    private Address address;

    @OneToMany(mappedBy = "member") // Order 테이블에 있는 member로 인하여 맵핑됨. 값을 넣는다고 해서 외래키가 바뀌지 않고 읽기만 가능
    private List<Order> orders = new ArrayList<>(); // 컬렉션은 바꾸지마라
}

