package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Auth;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import likelion.finalproject.market.member.repository.MemberRepository;
import likelion.finalproject.market.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberJoinService {
    private final MemberRepository memberRepository;
    private MemberUtil memberUtil;

    public ResponseMember register(RequestJoin requestJoin) {
        Auth auth = memberUtil.getAuth(requestJoin);
        requestJoin.setAuth(auth);
        Member member = memberRepository.save(requestJoin.toEntity());
        return memberUtil.getResponse(member);
    }
}