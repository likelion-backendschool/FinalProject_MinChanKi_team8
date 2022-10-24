package likelion.finalproject.market.product.application;

import likelion.finalproject.market.product.converter.ProductConverter;
import likelion.finalproject.market.product.domain.Product;
import likelion.finalproject.market.product.dto.param.ProductParam;
import likelion.finalproject.market.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductFindService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public ProductParam findOne(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 제품이 없습니다"));
        return productConverter.getProductParam(product);
    }
}
