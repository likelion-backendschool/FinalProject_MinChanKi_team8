package likelion.finalproject.market.member.application;

import likelion.finalproject.market.mail.application.MailComponent;
import likelion.finalproject.market.mail.dto.RequestSendEmail;
import likelion.finalproject.market.member.domain.Auth;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestJoin;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.member.repository.MemberRepository;
import likelion.finalproject.market.member.util.MemberUtil;
import likelion.finalproject.market.post.dto.request.RequestPost;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberJoinService {

    private final MailComponent mailComponent;
    private final MemberRepository memberRepository;
    private final MemberUtil memberUtil;
    private final MemberComponent memberComponent;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberParam register(RequestJoin requestJoin) {
        boolean isUniqueRequest = memberComponent.isUniqueRequest(requestJoin.getUsername(), requestJoin.getEmail(), requestJoin.getNickname());
        if(requestJoin.getNickname().isBlank())
            isUniqueRequest = memberComponent.isUniqueRequest(requestJoin.getUsername(), requestJoin.getEmail());

        if(!isUniqueRequest) {
            throw new IllegalArgumentException("중복된 정보가 있습니다");
        }

        Auth auth = memberUtil.getAuth(requestJoin);
        requestJoin.setAuth(auth);
        requestJoin.setPassword(passwordEncoder.encode(requestJoin.getPassword()));
        Member member = memberRepository.save(requestJoin.toEntity());
        mailComponent.sendMail(RequestSendEmail.builder()
                        .email(member.getEmail())
                        .title("가입 축하 메일")
                        .content("가입을 축하합니다!")
                        .build()
        );
        return memberUtil.getResponseMember(member);
    }


}