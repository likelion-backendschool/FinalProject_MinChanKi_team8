package likelion.finalproject.market.mail.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEmailSend {
    private String email;
    private String title;
    private String content;
}
