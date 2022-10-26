package likelion.finalproject.market.payment.controller;

import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.payment.domain.CashLog;
import likelion.finalproject.market.payment.domain.Event;
import likelion.finalproject.market.payment.repository.CashLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PayService {

    private final CashLogRepository cashLogRepository;

    @Transactional
    public void log(long money, Event event, MemberParam memberParam) {
        cashLogRepository.save(
                CashLog.builder()
                        .changePrice(money)
                        .event(event)
                        .member(memberParam.toEntity())
                        .build()
        );
    }
}
