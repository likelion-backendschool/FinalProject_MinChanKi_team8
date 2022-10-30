package likelion.finalproject.market.order.dto.param;

import likelion.finalproject.global.dto.BaseParam;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.order.domain.Order;
import likelion.finalproject.market.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderItemParam extends BaseParam {
    private long id;
    private LocalDate payDate;
    private int primeCost;
    private int cost;
    private int eventPrice;
    private int pgFee;
    private int payPrice;
    private int refundPrice;
    private boolean isPaid;
    private Order order;
    private Product product;
}
