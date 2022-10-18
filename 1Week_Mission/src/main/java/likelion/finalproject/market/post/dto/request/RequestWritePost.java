package likelion.finalproject.market.post.dto.request;

import likelion.finalproject.market.post.domain.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestWritePost {
    private String title;
    private String content;
    private String contentHtml;

    public Post toEntity() {
        return Post.builder()
                .subject(title)
                .content(content)
                .contentHtml(contentHtml)
                .build();
    }
}
