package likelion.finalproject.market.product.dto.param;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.post.domain.PostKeyword;
import likelion.finalproject.market.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductParam {
    private long id;
    private String subject;
    private int price;
    private LocalDate createDate;
    private LocalDate updateDate;
    private Member member;
    private PostKeyword postKeyword;

    public Product toEntity() {
        return Product.builder()
                .id(id)
                .subject(subject)
                .price(price)
                .createDate(createDate)
                .updateDate(updateDate)
                .member(member)
                .postKeyword(postKeyword)
                .build();
    }
}
