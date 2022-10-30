package likelion.finalproject.market.post.application;

import likelion.finalproject.market.post.domain.PostKeyword;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.market.post.repository.PostKeywordRepository;
import likelion.finalproject.market.post.util.PostKeywordUtil;
import likelion.finalproject.global.util.UtilComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PostKeywordService {

    private final PostKeywordUtil postKeywordUtil;
    private final PostKeywordRepository postKeywordRepository;

    @Transactional
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

    public List<PostKeywordParam> getPostKeywordParams(String keywords) {

        List<PostKeywordParam> postKeywordParams = new ArrayList<>();

        for(String keyword : postKeywordUtil.getKeywords(keywords)) {
             postKeywordRepository.findByContent(keyword).ifPresentOrElse(
                    postKeyword -> postKeywordParams.add(postKeywordUtil.getPostKeywordParam(postKeyword))
                     , () -> postKeywordParams.add(createKeyword(keyword)));
        }

        return postKeywordParams;
    }

    public PostKeywordParam getPostKeywordParam(String keyword) {
        PostKeyword postKeyword = postKeywordRepository.findByContent(keyword).orElseThrow(() -> new NoSuchElementException("존재하지 않는 키워드입니다"));

        return postKeywordUtil.getPostKeywordParam(postKeyword);
    }
}
