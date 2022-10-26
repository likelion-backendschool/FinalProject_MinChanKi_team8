package likelion.finalproject.market.member.application;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class MemberPayService {

    private final MemberRepository memberRepository;

    public long getCash(MemberParam memberParam) {
        Member member = memberRepository.findById(memberParam.getId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다"));
        return member.getCash();
    }

    @Transactional
    public void addCash(MemberParam memberParam, long money) {
        Member member = memberRepository.findById(memberParam.getId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다"));
        member.addCash(money);
    }

    @Transactional
    public void useCash(MemberParam memberParam, long money) {
        Member member = memberRepository.findById(memberParam.getId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 회원입니다"));
        member.useCash(money);
    }
}
