package likelion.finalproject.market.cart.repository;

import likelion.finalproject.market.cart.domain.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findByMemberId(long id);
    Optional<CartProduct> findByMemberIdAndProductId(long memberId, long productId);
}
