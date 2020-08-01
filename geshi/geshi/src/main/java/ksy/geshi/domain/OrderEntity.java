package ksy.geshi.domain;

import ksy.geshi.form.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@Table(name="orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity{

    @Id @GeneratedValue
    @Column(name="orders_Id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_Id")
    private MemberEntity member;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "orders", cascade=CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();

    private LocalDateTime time;


    public void setMember(MemberEntity member) {
        this.member=member;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static OrderEntity createOrder(MemberEntity member,OrderItem... orderItems){
        OrderEntity order=new OrderEntity();
        order.setMember(member);
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setStatus(OrderStatus.ORDER);
        order.setTime(LocalDateTime.now());
        return order;
    }

    public void cancelOrder() {
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}
