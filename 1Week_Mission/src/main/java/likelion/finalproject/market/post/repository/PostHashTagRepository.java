package likelion.finalproject.market.post.repository;

import likelion.finalproject.market.post.domain.PostHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long> {
}
