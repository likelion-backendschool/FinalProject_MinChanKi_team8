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

    @GetMapping("detail/{id}")
    public String detail(
            @PathVariable("id") long id
            , Model model
    ) {
        List<OrderItemParam> orderItemParams = orderItemService.getOrderItems(id);

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

        model.addAttribute("orderItems", orderItemsResponse);

        return "/order/detail";
    }

    @PostMapping("create")
    public String order(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());

        List<CartProductParam> cart = cartService.getCartList(memberParam.getId());

        // 주문 테이블 생성
        OrderParam orderParam = orderService.createOrder(memberParam);
        // 주문 품목 테이블 생성
        List<OrderItemParam> orderItemParams = orderItemService.createOrderItem(orderParam, cart);

        return "redirect:/order/detail/" + orderParam.getId();
    }

    @PostMapping("{id}/cancel")
    public String cancel(
            @PathVariable("id") long id
    ) {
        orderService.cancelOrder(id);
        return "redirect:/product/list";
    }
}
