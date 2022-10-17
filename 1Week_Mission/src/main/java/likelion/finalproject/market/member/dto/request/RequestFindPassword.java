package likelion.finalproject.market.member.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFindPassword {
    private String username;
    private String email;
}
