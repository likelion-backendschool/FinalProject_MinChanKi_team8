package likelion.finalproject.market.mail.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEmailSend {
    private String email;
    private String title;
    private String content;

    @Builder
    public RequestEmailSend(String email, String title, String content) {
        this.email = email;
        this.title = title;
        this.content = content;
    }
}
