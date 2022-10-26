package likelion.finalproject.market.member.dto.param;

import likelion.finalproject.global.dto.BaseParam;
import likelion.finalproject.market.member.domain.Auth;
import likelion.finalproject.market.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
public class MemberParam extends BaseParam {
    private long id;
    private String username;
    private String email;
    private String nickname;
    private long cash;
    private Auth auth;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .email(email)
                .nickname(nickname)
                .cash(cash)
                .auth(auth)
                .build();
    }
}