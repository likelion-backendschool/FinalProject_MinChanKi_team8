package likelion.finalproject.market.post.dto.request;

import likelion.finalproject.market.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPost {
    private String subject;
    private String content;
    private String contentHtml;
    private String keywords;

    public Post toEntity() {
        return Post.builder()
                .subject(subject)
                .content(content)
                .contentHtml(contentHtml)
                .build();
    }
}
