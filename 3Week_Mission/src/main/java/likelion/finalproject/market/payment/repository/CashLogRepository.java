package likelion.finalproject.market.payment.repository;

import likelion.finalproject.market.payment.domain.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashLogRepository extends JpaRepository<CashLog, Long> {
}
