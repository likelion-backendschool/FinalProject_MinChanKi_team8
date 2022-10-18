package likelion.finalproject.market.post.dto.param;

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
    private String writer;
    private String content;
    private String contentHtml;
    private LocalDate createDate;
    private LocalDate updateDate;

    @Builder
    public PostParam(long id, String subject, String writer, String content, String contentHtml, LocalDate createDate, LocalDate updateDate) {
        this.id = id;
        this.subject = subject;
        this.writer = writer;
        this.content = content;
        this.contentHtml = contentHtml;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
