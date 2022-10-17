package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestModify;
import likelion.finalproject.market.member.dto.request.RequestModifyPassword;
import likelion.finalproject.market.member.dto.response.ResponseMember;
import likelion.finalproject.market.member.repository.MemberRepository;
import likelion.finalproject.market.member.util.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberUpdateService {

    private final PasswordEncoder passwordEncoder;
    private final MemberUtil memberUtil;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseMember update(String username, RequestModify requestModify) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("해당하는 member가 존재하지 않습니다."));
        member.updateEmail(requestModify.getEmail());
        member.updateNickname(requestModify.getNickname());
        return memberUtil.getResponse(member);
    }

    @Transactional
    public ResponseMember updatePassword(String username, RequestModifyPassword requestModifyPassword) {
        if(!requestModifyPassword.getPassword().equals(requestModifyPassword.getPasswordConfirm()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");

        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("해당하는 member가 존재하지 않습니다."));
        if(passwordEncoder.matches(requestModifyPassword.getOldPassword(), member.getPassword())) {
            member.updatePassword(passwordEncoder.encode(requestModifyPassword.getPassword()));
            return memberUtil.getResponse(member);
        }

        throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");

    }
}
