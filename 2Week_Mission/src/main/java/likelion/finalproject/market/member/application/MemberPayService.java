package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberPayService {

    private final MemberRepository memberRepository;

    public int getCash(MemberParam memberParam) {
        Member member = memberRepository.findById(memberParam.getId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다"));
        return member.getCash();
    }

    public void addCash(MemberParam memberParam, int money) {
        Member member = memberRepository.findById(memberParam.getId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다"));
        member.addCash(money);
    }

    public void useCash(MemberParam memberParam, int money) {
        Member member = memberRepository.findById(memberParam.getId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다"));
        member.useCash(money);
    }
}
