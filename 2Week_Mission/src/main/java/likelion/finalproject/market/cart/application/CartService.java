package likelion.finalproject.market.cart.application;

import likelion.finalproject.market.cart.domain.CartProduct;
import likelion.finalproject.market.cart.dto.param.CartProductParam;
import likelion.finalproject.market.cart.repository.CartRepository;
import likelion.finalproject.market.cart.util.CartConverter;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.product.dto.param.ProductParam;
import likelion.finalproject.global.util.UtilComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartConverter cartConverter;

    public List<CartProductParam> getCartList(long id) {
        List<CartProduct> cartProducts = cartRepository.findByMemberId(id);
        return cartProducts.stream()
                .map(cartProduct -> cartConverter.getCartParam(cartProduct))
                .toList();
    }

    @Transactional
    public CartProductParam addProduct(MemberParam memberParam, ProductParam productParam) {
        Optional<CartProduct> cartOpt = cartRepository.findByMemberIdAndProductId(memberParam.getId(), productParam.getId());

        if(cartOpt.isPresent()) {
            throw new IllegalArgumentException("이미 장바구니에 존재합니다");
        }

        LocalDate now = UtilComponent.getDate();

        CartProduct cartProduct = cartRepository.save(CartProduct.builder()
                .createDate(now)
                .updateDate(now)
                .member(memberParam.toEntity())
                .product(productParam.toEntity())
                .build());

        return cartConverter.getCartParam(cartProduct);
    }

    @Transactional
    public void deleteProduct(long id) {
        cartRepository.deleteById(id);
    }
}
