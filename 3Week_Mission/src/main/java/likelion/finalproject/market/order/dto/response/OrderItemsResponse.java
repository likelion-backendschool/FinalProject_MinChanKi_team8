package likelion.finalproject.market.order.dto.response;

import likelion.finalproject.market.order.dto.param.OrderItemParam;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class OrderItemsResponse {
    private long orderId;
    private List<OrderItemParam> orderItemParams;
    private int totalPrice;
    private int totalQuantity;
}
