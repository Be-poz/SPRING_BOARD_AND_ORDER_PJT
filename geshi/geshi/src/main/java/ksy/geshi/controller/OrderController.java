package ksy.geshi.controller;

import ksy.geshi.form.ItemForm;
import ksy.geshi.form.OrderForm;
import ksy.geshi.service.ItemService;
import ksy.geshi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ItemService itemService;

    @GetMapping("/main")
    public String main(Model model){
        List<OrderForm> orderList = orderService.getOrderList();
        model.addAttribute("list",orderList);
        return "order/order_list";
    }

    @GetMapping("/request")
    public String request(Model model){
        List<ItemForm> items = itemService.findAll();
        model.addAttribute("items",items);
        return "order/order_request";
    }

    @PostMapping("/request")
    public String request_pro(@RequestParam("itemId")Long itemId,
                              @RequestParam("count")int count){
        orderService.order("temp",itemId,count);
        return "order/order_success";
    }

    @PostMapping("/{orderId}/cancel")
    public String cancel(@PathVariable("orderId")Long orderId){
        orderService.cancel(orderId);
        return "redirect:/order/main";
    }
}
