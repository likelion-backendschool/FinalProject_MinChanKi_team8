package likelion.finalproject.market.post.application;

import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.domain.PostHashTag;
import likelion.finalproject.market.post.domain.PostKeyword;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.repository.PostHashTagRepository;
import likelion.finalproject.market.post.util.PostKeywordUtil;
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

    private final PostKeywordUtil postKeywordUtil;
    private final PostHashTagRepository postHashTagRepository;

    @Transactional
    public PostParam createPostHashTag(PostParam postParam, PostKeywordParam postKeywordParam) {
        PostHashTag postHashTags = postHashTagRepository.save(getPostHashTag(postParam.toEntity(), postKeywordParam.toEntity()));
        return PostUtil.getPostParam(postHashTags.getPost());
    }

    @Transactional
    public PostParam createPostHashTag(PostParam postParam, List<PostKeywordParam> postKeywordParams) {
        List<PostHashTag> postHashTags = postHashTagRepository.saveAll(getPostHashTags(postParam.toEntity(), getPostKeywords(postKeywordParams)));
        return PostUtil.getPostParam(postHashTags.get(0).getPost());
    }

    private PostHashTag getPostHashTag(Post post, PostKeyword postKeyword) {
        LocalDate now = UtilComponent.getDate();

        return PostHashTag.builder()
                .createDate(now)
                .updateDate(now)
                .post(post)
                .postKeyword(postKeyword)
                .build();
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

    // postKeywordParam을 postHashTag에서 조회 (불편하지만 일단..)
    public String findKeywords(long postId) {
        List<PostHashTag> postHashTags = postHashTagRepository.findAllByPost_Id(postId);

        if(postHashTags.size() < 1)
            return "";

        StringBuilder sb = new StringBuilder();
        postHashTags.stream()
                .forEach(postHashTag ->
                        sb.append(postHashTag.getPostKeyword().getContent()).append(" ")
                );

        return sb.toString();
    }

    @Transactional
    public void updatePostHashTags(PostParam postParam, List<PostKeywordParam> postKeywordParams) {
        List<PostHashTag> postHashTags = postHashTagRepository.findAllByPost_Id(postParam.getId());

        postKeywordParams:
        for(PostKeywordParam postKeywordParam : postKeywordParams) {
            for(PostHashTag postHashTag : postHashTags) {
                if(postKeywordParam.getId() == postHashTag.getPostKeyword().getId()) {
                    continue postKeywordParams;
                }
            }
            createPostHashTag(postParam, postKeywordParam);
        }

        postHashTags:
        for(PostHashTag postHashTag : postHashTags) {
            for(PostKeywordParam postKeywordParam : postKeywordParams) {
                if(postKeywordParam.getId() == postHashTag.getPostKeyword().getId()) {
                    continue postHashTags;
                }
            }
            deletePostHashTag(postHashTag);
        }
    }

    @Transactional
    public void deletePostHashTag(PostHashTag postHashTag) {
        postHashTagRepository.delete(postHashTag);
    }
}