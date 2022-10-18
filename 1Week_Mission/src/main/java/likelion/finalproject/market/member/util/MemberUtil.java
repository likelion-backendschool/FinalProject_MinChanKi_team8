package likelion.finalproject.market.member.util;

import likelion.finalproject.market.member.domain.Auth;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import org.springframework.stereotype.Component;

@Component
public class MemberUtil {
    public ResponseMember getResponseMember(Member member) {
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

    public String getRandomPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'V', 'W', 'X', 'Y', 'Z' };

        int idx = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charSet.length; i++) {

            idx = (int) (charSet.length * Math.random());
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }
}
