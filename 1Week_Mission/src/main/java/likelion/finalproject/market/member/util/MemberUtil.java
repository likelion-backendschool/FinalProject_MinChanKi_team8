package likelion.finalproject.market.member.util;

import likelion.finalproject.market.member.domain.Auth;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.response.ResponseMember;

public class MemberUtil {
    public ResponseMember getResponse(Member member) {
        return ResponseMember.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

    public Auth getAuth(RequestJoin requestJoin) {
        if(requestJoin.getNickname().isBlank()) {
            return Auth.GENERAL;
        }

        return Auth.WRITER;
    }
}
