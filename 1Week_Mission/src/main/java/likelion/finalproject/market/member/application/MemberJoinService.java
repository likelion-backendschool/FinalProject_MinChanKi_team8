package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Auth;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import likelion.finalproject.market.member.repository.MemberRepository;
import likelion.finalproject.market.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberJoinService {

    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseMember register(RequestJoin requestJoin) {
        Auth auth = memberUtil.getAuth(requestJoin);
        requestJoin.setAuth(auth);
        requestJoin.setPassword(passwordEncoder.encode(requestJoin.getPassword()));
        Member member = memberRepository.save(requestJoin.toEntity());
        return memberUtil.getResponse(member);
    }
}