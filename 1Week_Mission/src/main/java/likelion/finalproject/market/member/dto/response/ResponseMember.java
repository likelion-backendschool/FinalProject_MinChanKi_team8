package likelion.finalproject.market.member.dto.response;

import likelion.finalproject.market.member.domain.Auth;
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
    private Auth auth;

    @Builder
    public ResponseMember(long id, String username, String email, String nickname, Auth auth) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.auth = auth;
    }
}