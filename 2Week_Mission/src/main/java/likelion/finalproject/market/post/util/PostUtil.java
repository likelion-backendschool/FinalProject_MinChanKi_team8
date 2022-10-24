package likelion.finalproject.market.post.util;

import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.dto.param.PostParam;
import org.springframework.stereotype.Component;

@Component
public class PostUtil {

    public static PostParam getPostParam(Post post) {
        return PostParam.builder()
                .id(post.getId())
                .subject(post.getSubject())
                .content(post.getContent())
                .contentHtml(post.getContentHtml())
                .member(post.getMember())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .build();
    }
}
