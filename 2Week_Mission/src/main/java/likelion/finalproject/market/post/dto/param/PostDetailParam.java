package likelion.finalproject.market.post.dto.param;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostDetailParam {
    private List<PostKeywordParam> postKeywordParams;
    private PostParam postParam;

    @Builder
    public PostDetailParam(List<PostKeywordParam> postKeywordParams, PostParam postParam) {
        this.postKeywordParams = postKeywordParams;
        this.postParam = postParam;
    }
}
