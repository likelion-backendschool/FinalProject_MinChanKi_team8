package likelion.finalproject.market.cart.util;

import likelion.finalproject.market.cart.domain.Cart;
import likelion.finalproject.market.cart.dto.param.CartParam;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.util.MemberUtil;
import likelion.finalproject.market.product.domain.Product;
import likelion.finalproject.market.product.util.ProductConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CartConverter {

    private final MemberUtil memberUtil;
    private final ProductConverter productConverter;

    public CartParam getCartParam(Cart cart) {
        Member member = cart.getMember();
        Product product = cart.getProduct();

        return CartParam.builder()
                .id(cart.getId())
                .memberParam(memberUtil.getResponseMember(member))
                .productParam(productConverter.getProductParam(product))
                .createDate(cart.getCreateDate())
                .updateDate(cart.getUpdateDate())
                .build();
    }
}
