package likelion.finalproject.market.post.application;

import likelion.finalproject.market.member.dto.response.ResponseMember;
import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.dto.request.RequestWritePost;
import likelion.finalproject.market.post.dto.response.ResponsePost;
import likelion.finalproject.market.post.repository.PostRepository;
import likelion.finalproject.market.post.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostUtil postUtil;
    private final PostRepository postRepository;

    @Transactional
    public ResponsePost create(RequestWritePost requestWritePost, ResponseMember member) {
        Post post = requestWritePost.toEntity();

        post.setCreateDate(getDate());
        post.setUpdateDate(getDate());
        post.setMember(member.toEntity());

        post = postRepository.save(post);

        return postUtil.getResponsePost(post);
    }

    private LocalDate getDate() {
        return LocalDate.now();
    }
}
