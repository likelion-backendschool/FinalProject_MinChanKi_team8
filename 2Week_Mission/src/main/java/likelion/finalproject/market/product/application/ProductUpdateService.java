package likelion.finalproject.market.product.application;

import likelion.finalproject.market.product.converter.ProductConverter;
import likelion.finalproject.market.product.domain.Product;
import likelion.finalproject.market.product.dto.param.ProductParam;
import likelion.finalproject.market.product.dto.request.RequestProductModify;
import likelion.finalproject.market.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductUpdateService {

    private final ProductConverter productConverter;
    private final ProductRepository productRepository;

    @Transactional
    public ProductParam updateProduct(RequestProductModify requestProductModify) {
        Product product = productRepository.findById(requestProductModify.getId()).orElseThrow(() -> new NoSuchElementException("해당하는 상품이 없습니다"));

        product.updateSubject(requestProductModify.getSubject());
        product.updatePrice(requestProductModify.getPrice());

        return productConverter.getProductParam(product);
    }
}
