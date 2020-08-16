package ksy.geshi.service;

import ksy.geshi.domain.ItemEntity;
import ksy.geshi.domain.MemberEntity;
import ksy.geshi.domain.OrderEntity;
import ksy.geshi.domain.OrderItem;
import ksy.geshi.form.OrderForm;
import ksy.geshi.repository.ItemRepository;
import ksy.geshi.repository.OrderRepository;
import ksy.geshi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public List<OrderForm> getOrderList() {
        List<OrderEntity> order_list = orderRepository.findAll();
        return order_list.stream()
                .map(o -> new OrderForm(o))
                .collect(Collectors.toList());
    }

    public void order(String temp_writer, Long itemId, int count) {
        MemberEntity member=userRepository.findById(temp_writer).get();
        ItemEntity item = itemRepository.findById(itemId).get();
        OrderItem orderItem=OrderItem.createOrderItem(item,item.getPrice(),count);
        OrderEntity order=OrderEntity.createOrder(member,orderItem);

        orderRepository.save(order);
    }

    public void cancel(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId).get();
        order.cancelOrder();
    }
}
