package ksy.geshi.form;


import ksy.geshi.domain.OrderEntity;
import ksy.geshi.domain.OrderItem;

import java.time.LocalDateTime;
import java.util.List;

public class OrderForm {
    public Long id;
    public String username;
    public List<OrderItem> item_list;
    public String status;
    public LocalDateTime time;

    public OrderForm(OrderEntity orderEntity){
        this.id=orderEntity.getId();
        this.username=orderEntity.getMember().getUsername();
        this.item_list=orderEntity.getOrderItems();
        this.status=orderEntity.getStatus().toString();
        this.time=orderEntity.getTime();
    }
}
