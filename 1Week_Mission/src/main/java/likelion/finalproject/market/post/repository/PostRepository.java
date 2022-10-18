package likelion.finalproject.market.post.repository;

import likelion.finalproject.market.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findTop100ByOrderByIdDesc();
    List<Post> findAllByOrderByIdDesc();
}
