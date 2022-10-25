package likelion.finalproject.market.order.repository;

import likelion.finalproject.market.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findByOrderId(long id);
}
