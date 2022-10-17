package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestModify;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import likelion.finalproject.market.member.repository.MemberRepository;
import likelion.finalproject.market.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberUpdateService {

    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseMember update(String username, RequestModify requestModify) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException(""));
        member.updateEmail(requestModify.getEmail());
        member.updateNickname(requestModify.getNickname());
        return memberUtil.getResponse(member);
    }
}
