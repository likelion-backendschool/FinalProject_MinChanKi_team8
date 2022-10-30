package likelion.finalproject.market.product.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductCreate {
    private String subject;
    private int price;
    private String keyword;
}
