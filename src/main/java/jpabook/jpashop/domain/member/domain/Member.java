package jpabook.jpashop.domain.member.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jpabook.jpashop.address.Address;
import jpabook.jpashop.domain.Enum.Gender;
import jpabook.jpashop.domain.order.domain.Order;
import jpabook.jpashop.model.attributeConverts.PhoneCryptoConverter;
import jpabook.jpashop.model.baseEntity.BaseTimeEntity;
import lombok.*;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
@ToString(exclude = {"phone","id"}, callSuper = true)
@Entity
@Getter
@Setter
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue // db가 자동으로 생성
    @Column(name = "member_id") // 기본키 테이블명
    private Long id;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "uid")
    private String uid; //

    @Column(name = "phone", length = 11, unique = true)
 //   @Convert(converter = PhoneCryptoConverter.class) // 변환하여 db 저장
    @JsonIgnore // 클라이언트에 응답, 요청 X
    private String phone;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "height")
    private String height;

    @Column(name = "job")
    private String job;

    @Column(name = "profile_url")
    private String profileUrl;

    @Column(name = "introduction")
    private String introduction;

    @Column(unique = true)
    private String name;

    @Embedded // @Embeddable과 둘 중 하나만 있어도 됨
    private Address address;

    @OneToMany(mappedBy = "member") // Order 테이블에 있는 member로 인하여 맵핑됨. 값을 넣는다고 해서 외래키가 바뀌지 않고 읽기만 가능
    private List<Order> orders = new ArrayList<>(); // 컬렉션은 바꾸지마라
}
