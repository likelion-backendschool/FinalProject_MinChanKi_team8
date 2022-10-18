package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.response.ResponseMember;
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

    public ResponseMember findMember(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("해당하는 member가 없습니다"));
        return memberUtil.getResponseMember(member);
    }
}
