package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.member.repository.MemberRepository;
import likelion.finalproject.market.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class MemberComponent {

    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;

    public MemberParam findMember(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("해당하는 member가 없습니다"));
        return memberUtil.getResponseMember(member);
    }

    public boolean isUniqueRequest(String username, String email) {
        if(isDuplicateUsername(username) || isDuplicateEmail(email))
            return false;
        return true;
    }

    public boolean isUniqueRequest(String username, String email, String nickname) {
        if(isDuplicateUsername(username) || isDuplicateEmail(email) || isDuplicateNickname(nickname))
            return false;
        return true;
    }

    public boolean isDuplicateEmail(String email) {
        if(memberRepository.findByEmail(email).isPresent())
            return true;
        return false;
    }

    public boolean isDuplicateUsername(String username) {
        if(memberRepository.findByUsername(username).isPresent())
            return true;
        return false;
    }

    public boolean isDuplicateNickname(String nickname) {
        if(memberRepository.findByNickname(nickname).isPresent())
            return true;
        return false;
    }
}
