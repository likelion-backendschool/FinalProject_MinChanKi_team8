package likelion.finalproject.market.cart.dto.param;

import likelion.finalproject.global.base.BaseParam;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CartProductParam extends BaseParam {
    private long id;
    private Member member;
    private Product product;
}
