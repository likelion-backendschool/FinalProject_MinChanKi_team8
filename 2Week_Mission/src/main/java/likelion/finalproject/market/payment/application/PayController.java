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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

        int memberCash = memberPayService.getCash(memberParam);

        if(totalPrice > memberCash) {
            charge(memberParam, totalPrice - memberCash);
        }

        payService.log(totalPrice, Event.PAY, memberParam);
        memberPayService.useCash(memberParam, totalPrice);
        orderService.order(id);

        return "redirect:/order/" + id;
    }

    public void charge(MemberParam memberParam, int money) {
        // 페이먼츠 충전

        // 후처리
        memberPayService.addCash(memberParam, money);
        payService.log(money, Event.CHARGE, memberParam);
    }
}
