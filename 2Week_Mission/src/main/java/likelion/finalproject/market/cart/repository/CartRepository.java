package likelion.finalproject.market.cart.repository;

import likelion.finalproject.market.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByMemberId(long id);
}
