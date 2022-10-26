package likelion.finalproject.market.order.controller;

import likelion.finalproject.market.cart.application.CartService;
import likelion.finalproject.market.cart.dto.param.CartProductParam;
import likelion.finalproject.market.member.application.MemberComponent;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.order.application.OrderItemService;
import likelion.finalproject.market.order.application.OrderService;
import likelion.finalproject.market.order.dto.param.OrderItemParam;
import likelion.finalproject.market.order.dto.param.OrderParam;
import likelion.finalproject.market.order.dto.response.OrderItemsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("order")
@Controller
public class OrderController {

    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final CartService cartService;
    private final MemberComponent memberComponent;

    @GetMapping("{id}")
    public String detail(
            @PathVariable("id") long id
            , Model model
    ) {
        OrderParam orderParam = orderService.findOrder(id);
        List<OrderItemParam> orderItemParams = orderItemService.findItems(id);

        OrderItemsResponse orderItemsResponse = OrderItemsResponse.builder()
                .orderId(id)
                .orderItemParams(orderItemParams)
                .totalPrice(orderItemParams.stream()
                        .mapToInt(orderItemParam -> {
                            if(orderItemParam.getEventPrice() > 0)
                                return orderItemParam.getEventPrice();
                            return orderItemParam.getCost();
                        })
                        .sum())
                .totalQuantity(orderItemParams.size())
                .build();

        model.addAttribute("order", orderParam);
        model.addAttribute("orderItems", orderItemsResponse);

        return "/order/detail";
    }

    @GetMapping("list")
    public String list(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());

        List<OrderParam> orderParams = orderService.findMyOrders(memberParam);

        model.addAttribute("orders", orderParams);

        return "/order/list";
    }

    @PostMapping("create")
    public String order(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());

        List<CartProductParam> cart = cartService.getCartList(memberParam.getId());

        OrderParam orderParam = orderService.createOrder(memberParam);
        orderItemService.createOrderItem(orderParam, cart);

        return "redirect:/order/" + orderParam.getId();
    }

    @PostMapping("{id}/cancel")
    public String cancel(
            @PathVariable("id") long id
    ) {
        orderService.cancelOrder(id);
        return "redirect:/order/list";
    }
}
