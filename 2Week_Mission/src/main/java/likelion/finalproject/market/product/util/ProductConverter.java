package likelion.finalproject.market.product.util;

import likelion.finalproject.market.product.domain.Product;
import likelion.finalproject.market.product.dto.param.ProductParam;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<ProductParam> getProductParams(List<Product> products) {
        return products.stream()
                .map(product -> getProductParam(product))
                .toList();
    }
}
