package likelion.finalproject.market.post.util;

import likelion.finalproject.market.post.domain.PostKeyword;
import likelion.finalproject.market.post.dto.param.PostKeywordParam;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PostKeywordUtil {;
    public PostKeywordParam getPostKeywordParam(PostKeyword postKeyword) {
        return PostKeywordParam.builder()
                .id(postKeyword.getId())
                .content(postKeyword.getContent())
                .createDate(postKeyword.getCreateDate())
                .updateDate(postKeyword.getUpdateDate())
                .build();
    }

    public List<String> getKeywords(String keywords) {
        List<String> postKeywords = Arrays.stream(keywords.split(" "))
                .filter(keyword -> keyword.contains("#"))
                .toList();

        return postKeywords;
    }
}
