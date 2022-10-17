package likelion.finalproject.market.member.repository;

import likelion.finalproject.market.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
