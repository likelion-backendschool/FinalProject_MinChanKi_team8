package likelion.finalproject.market.product.application;

import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.product.util.ProductConverter;
import likelion.finalproject.market.product.domain.Product;
import likelion.finalproject.market.product.dto.param.ProductParam;
import likelion.finalproject.market.product.dto.request.RequestProductCreate;
import likelion.finalproject.market.product.dto.request.RequestProductModify;
import likelion.finalproject.market.product.repository.ProductRepository;
import likelion.finalproject.global.util.UtilComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductConverter productConverter;
    private final ProductRepository productRepository;

    public ProductParam createProduct(
            RequestProductCreate requestProductCreate
            , MemberParam memberParam
            , PostKeywordParam postKeywordParam
    ) {
        Product product = productRepository.save(
                Product.builder()
                .subject(requestProductCreate.getSubject())
                .price(requestProductCreate.getPrice())
                .createDate(UtilComponent.getDate())
                .updateDate(UtilComponent.getDate())
                .member(memberParam.toEntity())
                .postKeyword(postKeywordParam.toEntity())
                .build()
        );

        return productConverter.getProductParam(product);
    }

    public ProductParam findOne(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException("해당하는 제품이 없습니다"));
        return productConverter.getProductParam(product);
    }

    public List<ProductParam> findAll() {
        List<Product> products = productRepository.findAll();
        return productConverter.getProductParams(products);
    }

    @Transactional
    public ProductParam updateProduct(RequestProductModify requestProductModify) {
        Product product = productRepository.findById(requestProductModify.getId()).orElseThrow(() -> new NoSuchElementException("해당하는 상품이 없습니다"));

        product.updateSubject(requestProductModify.getSubject());
        product.updatePrice(requestProductModify.getPrice());

        return productConverter.getProductParam(product);
    }

    @Transactional
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
