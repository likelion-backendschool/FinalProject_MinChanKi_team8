package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestFindPassword;
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

    public ResponseMember findMember(RequestFindPassword requestFindPassword) {
        Member member = memberRepository.findByUsername(requestFindPassword.getUsername()).orElseThrow(() -> new NoSuchElementException("해당하는 member가 존재하지 않습니다."));

        if(member.getEmail().equals(requestFindPassword.getEmail())) {
            return memberUtil.getResponse(member);
        }

        throw new NoSuchElementException("해당하는 member가 존재하지 않습니다.");
    }
}
