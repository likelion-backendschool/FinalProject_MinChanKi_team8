package likelion.finalproject.market.post.dto.param;

import likelion.finalproject.market.member.domain.Member;
import likelion.finalproject.market.post.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class PostParam {
    private long id;
    private String subject;
    private String content;
    private String contentHtml;
    private LocalDate createDate;
    private LocalDate updateDate;
    private Member member;
    private String keywords;

    @Builder
    public PostParam(long id, String subject, String content, String contentHtml, LocalDate createDate, LocalDate updateDate, Member member, String keywords) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.contentHtml = contentHtml;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.member = member;
        this.keywords = keywords;
    }

    public Post toEntity() {
        return Post.builder()
                .id(id)
                .subject(subject)
                .content(content)
                .contentHtml(contentHtml)
                .createDate(createDate)
                .updateDate(updateDate)
                .member(member)
                .build();
    }
}
