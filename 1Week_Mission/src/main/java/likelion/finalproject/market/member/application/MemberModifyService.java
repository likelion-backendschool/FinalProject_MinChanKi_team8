package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.request.RequestModify;
import likelion.finalproject.market.member.dto.request.RequestModifyPassword;
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
public class MemberModifyService {

    private final PasswordEncoder passwordEncoder;
    private final MemberUtil memberUtil;
    private final MemberComponent memberComponent;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberParam update(String username, RequestModify requestModify) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("해당하는 member가 존재하지 않습니다."));

        if(!requestModify.getEmail().isBlank()) {
            if(memberComponent.isDuplicateEmail(requestModify.getEmail())) {
                throw new IllegalArgumentException("중복된 이메일입니다");
            }
            member.updateEmail(requestModify.getEmail());
        }

        if(!requestModify.getNickname().isBlank()) {
            if(memberComponent.isDuplicateNickname(requestModify.getNickname())) {
                throw new IllegalArgumentException("중복된 닉네임입니다");
            }
            member.updateNickname(requestModify.getNickname());
            member.setWriter();
        }

        return memberUtil.getResponseMember(member);
    }

    @Transactional
    public MemberParam updatePassword(String username, RequestModifyPassword requestModifyPassword) {
        if(!requestModifyPassword.getPassword().equals(requestModifyPassword.getPasswordConfirm()))
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");

        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("해당하는 member가 존재하지 않습니다."));

        if(passwordEncoder.matches(requestModifyPassword.getOldPassword(), member.getPassword())) {
            member.updatePassword(passwordEncoder.encode(requestModifyPassword.getPassword()));
            return memberUtil.getResponseMember(member);
        }

        throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
    }
}
