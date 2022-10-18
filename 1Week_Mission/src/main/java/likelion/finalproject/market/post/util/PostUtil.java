package likelion.finalproject.market.post.util;

import likelion.finalproject.market.post.domain.Post;
import likelion.finalproject.market.post.dto.response.ResponsePost;
import org.springframework.stereotype.Component;

@Component
public class PostUtil {

    public ResponsePost getResponsePost(Post post) {
        return ResponsePost.builder()
                .id(post.getId())
                .subject(post.getSubject())
                .content(post.getContent())
                .writer(post.getMember().getUsername())
                .createDate(post.getCreateDate())
                .updateDate(post.getUpdateDate())
                .build();
    }
}
