package likelion.finalproject.market.post.application;

import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.dto.request.RequestWritePost;
import likelion.finalproject.market.post.dto.response.ResponsePost;
import likelion.finalproject.market.post.repository.PostRepository;
import likelion.finalproject.market.post.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostUtil postUtil;
    private final PostRepository postRepository;

    public ResponsePost create(RequestWritePost requestWritePost) {
        Post post = requestWritePost.toEntity();
        post.setCreateDate(getDate());
        post.setUpdateDate(getDate());
        post = postRepository.save(requestWritePost.toEntity());
        return postUtil.getResponsePost(post);
    }

    private LocalDate getDate() {
        return LocalDate.now();
    }
}
