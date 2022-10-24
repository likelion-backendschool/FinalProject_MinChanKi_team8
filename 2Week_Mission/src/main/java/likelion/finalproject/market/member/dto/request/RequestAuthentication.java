package likelion.finalproject.market.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestAuthentication {
    private String username;
    private String password;
}
