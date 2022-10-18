package likelion.finalproject.market.post.application;

import likelion.finalproject.market.member.dto.param.MemberParam;
import likelion.finalproject.market.member.util.MemberUtil;
import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.dto.request.RequestWritePost;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.repository.PostRepository;
import likelion.finalproject.market.post.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostUtil postUtil;
    private final PostRepository postRepository;

    @Transactional
    public PostParam create(RequestWritePost requestWritePost, MemberParam member) {
        Post post = requestWritePost.toEntity();

        post.setCreateDate(getDate());
        post.setUpdateDate(getDate());
        post.setMember(member.toEntity());

        post = postRepository.save(post);

        return postUtil.getResponsePost(post);
    }

    public List<PostParam> getIndexPosts() {
        List<Post> posts = postRepository.findTop100ByOrderByIdDesc();
        return posts.stream()
                .map(PostUtil::getResponsePost)
                .toList();
    }

    private LocalDate getDate() {
        return LocalDate.now();
    }
}
