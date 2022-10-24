package likelion.finalproject.market.product.application;

import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.product.converter.ProductConverter;
import likelion.finalproject.market.product.domain.Product;
import likelion.finalproject.market.product.dto.param.ProductParam;
import likelion.finalproject.market.product.dto.request.RequestProductCreate;
import likelion.finalproject.market.product.repository.ProductRepository;
import likelion.finalproject.util.UtilComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductCreateService {

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
}
