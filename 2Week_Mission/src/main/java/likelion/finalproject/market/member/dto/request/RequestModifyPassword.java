package likelion.finalproject.market.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModifyPassword {
    private String oldPassword;
    private String password;
    private String passwordConfirm;
}
