package likelion.finalproject.market.order.application;

import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.order.domain.Order;
import likelion.finalproject.market.order.dto.param.OrderParam;
import likelion.finalproject.market.order.repository.OrderRepository;
import likelion.finalproject.market.order.util.OrderConverter;
import likelion.finalproject.global.util.UtilComponent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final ModelMapper modelMapper;

    public OrderParam findOrder(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 주문이 없습니다"));
        return modelMapper.map(order, OrderParam.class);
    }

    public List<OrderParam> findMyOrders(MemberParam memberParam) {
        List<Order> orders = orderRepository.findAllByMemberId(memberParam.getId());
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderParam.class))
                .toList();
    }

    @Transactional
    public OrderParam createOrder(MemberParam memberParam) {
        LocalDate now = UtilComponent.getDate();
        Order order = orderRepository.save(
                Order.builder()
                        .createDate(now)
                        .updateDate(now)

                        .member(memberParam.toEntity())
                        .build()
        );

        return orderConverter.getOrderParam(order);
    }

    @Transactional
    public void cancelOrder(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 주문이 없습니다"));
        order.cancel();
    }
}
