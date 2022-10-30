package likelion.finalproject.market.order.util;

import likelion.finalproject.market.order.domain.Order;
import likelion.finalproject.market.order.dto.param.OrderParam;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    public OrderParam getOrderParam(Order order) {
        return OrderParam.builder()
                .id(order.getId())
                .createDate(order.getCreateDate())
                .updateDate(order.getUpdateDate())
                .isOrdered(order.isOrdered())
                .isPaid(order.isPaid())
                .isCanceled(order.isCanceled())
                .isRefunded(order.isRefunded())
                .member(order.getMember())
                .build();
    }
}
