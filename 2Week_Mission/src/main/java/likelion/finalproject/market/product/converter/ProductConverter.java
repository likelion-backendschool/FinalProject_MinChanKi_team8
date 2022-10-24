package likelion.finalproject.market.product.converter;

import likelion.finalproject.market.product.domain.Product;
import likelion.finalproject.market.product.dto.param.ProductParam;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductParam getProductParam(Product product) {
        return ProductParam.builder()
                .id(product.getId())
                .subject(product.getSubject())
                .price(product.getPrice())
                .createDate(product.getCreateDate())
                .updateDate(product.getUpdateDate())
                .member(product.getMember())
                .postKeyword(product.getPostKeyword())
                .build();
    }
}
