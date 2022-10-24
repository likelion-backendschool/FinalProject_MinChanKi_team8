package likelion.finalproject.market.cart.controller;

import likelion.finalproject.market.cart.application.CartService;
import likelion.finalproject.market.cart.dto.param.CartParam;
import likelion.finalproject.market.member.application.MemberComponent;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.product.application.ProductService;
import likelion.finalproject.market.product.dto.param.ProductParam;
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
@RequestMapping("cart")
@Controller
public class CartController {

    private final MemberComponent memberComponent;
    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("list")
    public String list(
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());

        List<CartParam> cartParams = cartService.getCartList(memberParam.getId());

        model.addAttribute("carts", cartParams);

        return "/cart/list";
    }

    @PostMapping("add/{productId}")
    public String add(
            @PathVariable("productId") long productId
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberParam memberParam = memberComponent.findMember(authentication.getName());

        ProductParam productParam = productService.findOne(productId);

        cartService.addProduct(memberParam, productParam);

        return "redirect:/cart/list";
    }

    @PostMapping("remove/{id}")
    public String remove(
            @PathVariable("id") long id
    ) {
        cartService.deleteProduct(id);

        return "redirect:/cart/list";
    }
}
