package likelion.finalproject.market.cart.dto.param;

import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.product.dto.param.ProductParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartParam {
    private long id;
    private LocalDate createDate;
    private LocalDate updateDate;
    private MemberParam memberParam;
    private ProductParam productParam;
}
