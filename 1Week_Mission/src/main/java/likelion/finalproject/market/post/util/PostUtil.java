package likelion.finalproject.market.post.util;

import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.dto.param.PostParam;
import org.springframework.stereotype.Component;

@Component
public class PostUtil {

    public static PostParam getResponsePost(Post post) {
        return PostParam.builder()
                .id(post.getId())
                .subject(post.getSubject())
                .content(post.getContentHtml())
                .writer(post.getMember().getUsername())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .build();
    }
}
