package likelion.finalproject.market.post.repository;

import likelion.finalproject.market.post.domain.PostKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostKeywordRepository extends JpaRepository<PostKeyword, Long> {
    Optional<PostKeyword> findByContent(String content);
}
