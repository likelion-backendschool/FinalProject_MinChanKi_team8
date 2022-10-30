package likelion.finalproject.market.order.dto.param;

import likelion.finalproject.global.base.BaseParam;
import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.order.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class OrderParam extends BaseParam {
    private long id;
    private LocalDate payDate;
    private boolean isOrdered;
    private boolean isPaid;
    private boolean isCanceled;
    private boolean isRefunded;
    private Member member;

    public Order toEntity() {
        return Order.builder()
                .id(id)
                .createDate(getCreateDate())
                .updateDate(getUpdateDate())
                .payDate(payDate)
                .isOrdered(isOrdered)
                .isPaid(isPaid)
                .isCanceled(isCanceled)
                .isRefunded(isRefunded)
                .member(member)
                .build();
    }
}
