package likelion.finalproject.market.product.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class RequestProductModify {
    private long id;
    private String subject;
    private int price;
    private String keyword;
}