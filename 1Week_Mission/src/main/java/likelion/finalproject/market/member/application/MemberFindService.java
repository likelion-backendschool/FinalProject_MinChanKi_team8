package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestFindUsername;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import likelion.finalproject.market.member.repository.MemberRepository;
import likelion.finalproject.market.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberFindService {

    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;

    public ResponseMember findUsername(RequestFindUsername requestFindUsername) {
        Member member = memberRepository.findByEmail(requestFindUsername.getEmail()).orElseThrow(() -> new NoSuchElementException("해당하는 유저가 없습니다"));

        return memberUtil.getResponse(member);
    }
}
