package likelion.finalproject.market.post.application;

import likelion.finalproject.market.post.domain.PostHashTag;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.dto.param.PostParam;
import likelion.finalproject.market.post.repository.PostHashTagRepository;
import likelion.finalproject.market.post.util.PostHashTagUitl;
import likelion.finalproject.market.post.util.PostUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostHashTagService {

    private final PostHashTagUitl postHashTagUitl;
    private final PostHashTagRepository postHashTagRepository;

    @Transactional
    public PostParam createPostHashTag(PostParam postParam, PostKeywordParam postKeywordParam) {
        PostHashTag postHashTags = postHashTagRepository.save(postHashTagUitl.getPostHashTag(postParam.toEntity(), postKeywordParam.toEntity()));
        return PostUtil.getPostParam(postHashTags.getPost());
    }

    @Transactional
    public PostParam createPostHashTag(PostParam postParam, List<PostKeywordParam> postKeywordParams) {
        List<PostHashTag> postHashTags = postHashTagUitl.getPostHashTags(postParam.toEntity(), postHashTagUitl.getPostKeywords(postKeywordParams));
        postHashTagRepository.saveAll(postHashTags);

        return PostUtil.getPostParam(postHashTags.get(0).getPost());
    }

    // postKeywordParam을 postHashTag에서 조회 -> 추후 postKeyword에서 Query를 이용하여 처리
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
