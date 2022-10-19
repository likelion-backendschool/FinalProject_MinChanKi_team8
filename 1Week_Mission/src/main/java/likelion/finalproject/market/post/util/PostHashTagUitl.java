package likelion.finalproject.market.post.util;

import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.domain.PostHashTag;
import likelion.finalproject.market.post.domain.PostKeyword;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import likelion.finalproject.util.UtilComponent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PostHashTagUitl {
    public PostHashTag getPostHashTag(Post post, PostKeyword postKeyword) {
        LocalDate now = UtilComponent.getDate();

        return PostHashTag.builder()
                .createDate(now)
                .updateDate(now)
                .post(post)
                .postKeyword(postKeyword)
                .build();
    }

    public List<PostHashTag> getPostHashTags(Post post, List<PostKeyword> postKeywords) {
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

    public List<PostKeyword> getPostKeywords(List<PostKeywordParam> postKeywordParams) {
        return postKeywordParams.stream()
                .map(PostKeywordParam::toEntity
                ).toList();
    }
}
