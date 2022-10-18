package likelion.finalproject.market.post.application;

import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.domain.PostHashTag;
import likelion.finalproject.market.post.domain.PostKeyword;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.repository.PostHashTagRepository;
import likelion.finalproject.market.post.util.PostUtil;
import likelion.finalproject.util.UtilComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostHashTagService {

    private final PostHashTagRepository postHashTagRepository;

    @Transactional
    public PostParam createPostHashTags(PostParam postParam, List<PostKeywordParam> postKeywordParams) {
        List<PostHashTag> postHashTags = postHashTagRepository.saveAll(getPostHashTags(postParam.toEntity(), getPostKeywords(postKeywordParams)));
        return PostUtil.getPostParam(postHashTags.get(0).getPost());
    }

    private List<PostHashTag> getPostHashTags(Post post, List<PostKeyword> postKeywords) {
        LocalDate now = UtilComponent.getDate();

        return postKeywords.stream()
                .map(postKeyword ->
                        PostHashTag.builder()
                                .createDate(now)
                                .updateDate(now)
                                .post(post)
                                .postKeyword(postKeyword)
                                .build()
                ).toList();
    }

    private List<PostKeyword> getPostKeywords(List<PostKeywordParam> postKeywordParams) {
        return postKeywordParams.stream()
                .map(PostKeywordParam::toEntity
                ).toList();
    }
}
