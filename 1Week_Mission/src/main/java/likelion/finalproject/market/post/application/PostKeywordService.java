package likelion.finalproject.market.post.application;

import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.domain.PostKeyword;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.repository.PostKeywordRepository;
import likelion.finalproject.market.post.util.PostKeywordUtil;
import likelion.finalproject.util.UtilComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostKeywordService {

    private final PostKeywordUtil postKeywordUtil;
    private final PostKeywordRepository postKeywordRepository;

    public PostKeywordParam createKeyword(String keyword) {
        LocalDate now = UtilComponent.getDate();

        PostKeyword postKeyword = postKeywordRepository.save(
                PostKeyword.builder()
                        .content(keyword)
                        .createDate(now)
                        .updateDate(now)
                        .build()
        );

        return postKeywordUtil.getPostKeywordParam(postKeyword);
    }

    public List<PostKeywordParam> getUniqueKeywordParams(String keywords) {

        List<PostKeywordParam> postKeywordParams = new ArrayList<>();

        for(String keyword : postKeywordUtil.getKeywords(keywords)) {
             postKeywordRepository.findByContent(keyword).ifPresentOrElse(
                    postKeyword -> postKeywordParams.add(postKeywordUtil.getPostKeywordParam(postKeyword))
                     , () -> postKeywordParams.add(createKeyword(keyword)));
        }

        return postKeywordParams;
    }
}
