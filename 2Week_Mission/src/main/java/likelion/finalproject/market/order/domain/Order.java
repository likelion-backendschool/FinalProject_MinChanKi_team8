package likelion.finalproject.market.order.domain;

import likelion.finalproject.global.base.BaseTimeEntity;
import likelion.finalproject.market.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "orders")
@Entity
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private LocalDate payDate;

    // 주문 완료 여부
    @Column
    private boolean isOrdered = false;

    // 결제 여부
    @Column
    private boolean isPaid = false;

    // 취소 여부
    @Column
    private boolean isCanceled = false;

    // 환불 여부
    @Column
    private boolean isRefunded = false;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public void cancel() {
        this.isCanceled = true;
    }

    public void refund() {
        this.isRefunded = true;
    }

    public void order() {
        this.isOrdered = true;
        this.isPaid = true;
    }
}
