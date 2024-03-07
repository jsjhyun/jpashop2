package jpabook.jpashop.domain.order.service;

import jpabook.jpashop.domain.Enum.DeliveryStatus;
import jpabook.jpashop.domain.delivery.domain.Delivery;
import jpabook.jpashop.domain.item.domain.Item;
import jpabook.jpashop.domain.item.repository.ItemRepository;
import jpabook.jpashop.domain.member.domain.Member;
import jpabook.jpashop.domain.member.repository.MemberRepository;
import jpabook.jpashop.domain.order.domain.Order;
import jpabook.jpashop.domain.order.domain.OrderItem;
import jpabook.jpashop.domain.order.domain.OrderSearch;
import jpabook.jpashop.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    /**
     * 주문
     */
    @Transactional // 데이터 변경
    public Long order(Long memberId, Long itemId, int count){

        // 엔티티 조회
        Member member = memberRepository.findById(memberId).orElse(null);
        Item item = itemRepository.findById(itemId).orElse(null);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress()); // 회원 주소로 배송을 한다. - 예시
        delivery.setStatus(DeliveryStatus.READY);

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order order = orderRepository.findById(orderId);
        // 주문 취소
        order.cancel();
    }

    /**
     * 주문 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }
}
