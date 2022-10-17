package likelion.finalproject.market.member.dto.request;

import likelion.finalproject.market.member.domain.Auth;
import likelion.finalproject.market.member.domain.Member;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class RequestJoin {
    private String username;
    private String email;
    @Nullable
    private String nickname;
    private String password;
    private Auth auth;

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .email(email)
                .nickname(nickname)
                .password(password)
                .auth(auth)
                .build();
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }
}