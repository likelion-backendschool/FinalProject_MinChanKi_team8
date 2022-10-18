package likelion.finalproject.market.post.repository;

import likelion.finalproject.market.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
