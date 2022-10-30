package likelion.finalproject.market.cart.util;

import likelion.finalproject.market.cart.domain.CartProduct;
import likelion.finalproject.market.cart.dto.param.CartProductParam;
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

    public CartProductParam getCartParam(CartProduct cartProduct) {
        Member member = cartProduct.getMember();
        Product product = cartProduct.getProduct();

        return CartProductParam.builder()
                .id(cartProduct.getId())
                .member(member)
                .product(product)
                .createDate(cartProduct.getCreateDate())
                .updateDate(cartProduct.getUpdateDate())
                .build();
    }
}
