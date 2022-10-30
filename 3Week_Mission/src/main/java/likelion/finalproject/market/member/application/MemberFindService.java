package likelion.finalproject.market.member.application;

import likelion.finalproject.market.mail.application.MailComponent;
import likelion.finalproject.market.mail.dto.RequestSendEmail;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestFindPassword;
import likelion.finalproject.market.member.dto.request.RequestFindUsername;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.member.repository.MemberRepository;
import likelion.finalproject.market.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberFindService {

    private final MailComponent mailComponent;
    private final MemberUtil memberUtil;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public MemberParam findMember(RequestFindUsername requestFindUsername) {
        Member member = memberRepository.findByEmail(requestFindUsername.getEmail()).orElseThrow(() -> new NoSuchElementException("해당하는 유저가 없습니다"));

        return memberUtil.getResponseMember(member);
    }

    @Transactional
    public MemberParam findMember(RequestFindPassword requestFindPassword) {
        Member member = memberRepository.findByUsername(requestFindPassword.getUsername()).orElseThrow(() -> new NoSuchElementException("해당하는 member가 존재하지 않습니다."));

        if(member.getEmail().equals(requestFindPassword.getEmail())) {
            String tempPassword = memberUtil.getRandomPassword();
            member.updatePassword(passwordEncoder.encode(tempPassword));
            mailComponent.sendMail(
                    RequestSendEmail.builder()
                            .email(member.getEmail())
                            .title("임시 비밀번호 발급")
                            .content(tempPassword)
                            .build()
            );

            return memberUtil.getResponseMember(member);
        }

        throw new NoSuchElementException("해당하는 member가 존재하지 않습니다.");
    }
}
