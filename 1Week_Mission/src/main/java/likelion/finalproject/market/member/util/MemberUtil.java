package likelion.finalproject.market.member.util;

import likelion.finalproject.market.member.domain.Auth;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import org.springframework.stereotype.Component;

@Component
public class MemberUtil {
    public ResponseMember getResponse(Member member) {
        return ResponseMember.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .auth(member.getAuth())
                .build();
    }

    public Auth getAuth(RequestJoin requestJoin) {
        if(requestJoin.getNickname().isBlank()) {
            return Auth.GENERAL;
        }

        return Auth.WRITER;
    }
}
