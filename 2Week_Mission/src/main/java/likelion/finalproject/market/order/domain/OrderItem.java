package likelion.finalproject.market.order.domain;

import likelion.finalproject.global.domain.BaseTimeEntity;
import likelion.finalproject.market.product.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class OrderItem extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private LocalDate payDate;

    // 원가
    @Column
    private int primeCost = 0;

    // 정가
    @Column
    private int cost = 0;

    // 할인가
    @Column
    private int eventPrice = 0;

    // 결제 대행사 수수료
    @Column
    private int pgFee = 0;

    // 결제 금액
    @Column
    private int payPrice = 0;

    // 환불금액
    @Column
    private int refundPrice = 0;

    // 결제여부
    @Column
    private boolean isPaid = false;
}
