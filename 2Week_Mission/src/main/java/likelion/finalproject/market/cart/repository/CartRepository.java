package likelion.finalproject.market.cart.repository;

import likelion.finalproject.market.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByMemberId(long id);
    Optional<Cart> findByMemberIdAndProductId(long memberId, long productId);
}
