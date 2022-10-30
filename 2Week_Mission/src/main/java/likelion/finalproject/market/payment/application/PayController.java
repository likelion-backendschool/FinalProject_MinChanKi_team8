package likelion.finalproject.market.payment.application;

import likelion.finalproject.market.member.application.MemberComponent;
import likelion.finalproject.market.member.application.MemberPayService;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.order.application.OrderItemService;
import likelion.finalproject.market.order.application.OrderService;
import likelion.finalproject.market.payment.controller.PayService;
import likelion.finalproject.market.payment.domain.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class PayController {

    private final PayService payService;
    private final MemberPayService memberPayService;
    private final MemberComponent memberComponent;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping("/order/{id}/pay")
    public String pay(
            @PathVariable("id") long id
    ) {
        int totalPrice = orderItemService.findTotalPrice(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());

        long memberCash = memberPayService.getCash(memberParam);

        if(totalPrice > memberCash) {
            return "redirect:/order/" + id;
        }

        payService.log(totalPrice, Event.PAY, memberParam);
        memberPayService.useCash(memberParam, totalPrice);
        orderService.order(id);

        return "redirect:/order/" + id;
    }

    public void charge(MemberParam memberParam, int money) {
        // 페이먼츠 충전

        // 후처리

    }

    @GetMapping("/order/{id}/charge/success")
    public String charge(
            @PathVariable("id") long id
            ,@RequestParam String paymentKey
            , @RequestParam String orderId
            , @RequestParam long amount
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());

        memberPayService.addCash(memberParam, amount);
        payService.log(amount, Event.CHARGE, memberParam);

        return "redirect:/order/" + id;
    }
}
