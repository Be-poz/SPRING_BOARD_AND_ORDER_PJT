package ksy.geshi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="item_id")
    private ItemEntity item;

    @JsonIgnore
    @ManyToOne(fetch=LAZY)
    @JoinColumn(name="orders_id")
    private OrderEntity orders;

    private int orderPrice; //주문 가격

    private int count; //주문 수량

    public void setOrder(OrderEntity order) {
        this.orders=order;
    }

    //생성 메서드
    public static OrderItem createOrderItem(ItemEntity item,int orderPrice,int count){
        OrderItem orderItem=new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    public void cancel(){
        getItem().addStock(count);
    }

}
