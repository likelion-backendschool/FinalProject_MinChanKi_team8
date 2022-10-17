package likelion.finalproject.market.member.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResponseMember {
    private long id;
    private String username;
    private String email;
    private String nickname;

    @Builder
    public ResponseMember(long id, String username, String email, String nickname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
    }
}