package likelion.finalproject.market.order.util;

import likelion.finalproject.market.order.dto.param.OrderItemParam;

import java.util.List;

public class PriceUtil {

    public int getTotalPrice(List<OrderItemParam> orderItemParams) {
        int totalPrice = 0;
        for(OrderItemParam orderItemParam : orderItemParams) {
            if(orderItemParam.getEventPrice() > 0)
                totalPrice += orderItemParam.getEventPrice();
            else
                totalPrice += orderItemParam.getCost();
        }


        return totalPrice;
    }
}
