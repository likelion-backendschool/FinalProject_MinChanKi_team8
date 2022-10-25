package likelion.finalproject.market.order.application;

import likelion.finalproject.market.cart.dto.param.CartProductParam;
import likelion.finalproject.market.order.domain.Order;
import likelion.finalproject.market.order.domain.OrderItem;
import likelion.finalproject.market.order.dto.param.OrderItemParam;
import likelion.finalproject.market.order.dto.param.OrderParam;
import likelion.finalproject.market.order.repository.OrderItemRepository;
import likelion.finalproject.market.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    public List<OrderItemParam> getOrderItems(long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        return orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemParam.class))
                .toList();
    }

    @Transactional
    public List<OrderItemParam> createOrderItem(OrderParam orderParam, List<CartProductParam> cart) {
        List<OrderItem> orderItems = cart.stream()
                .map(cartProduct -> {
                            Product product = cartProduct.getProduct();
                            return OrderItem.builder()
                                    .order(orderParam.toEntity())
                                    .product(product)
                                    .primeCost(getPrimeCost(product.getPrice()))
                                    .cost(product.getPrice())
                                    .build();
                        }
                )
                .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);

        return orderItems.stream().map(
                        orderItem -> modelMapper.map(orderItem, OrderItemParam.class))
                .toList();
    }

    private int getPrimeCost(int cost) {
        return (int) (cost / 1.6);
    }
}
