package likelion.finalproject.market.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ResponsePost {
    private long id;
    private String subject;
    private String writer;
    private String content;
    private LocalDate createDate;
    private LocalDate updateDate;

    @Builder
    public ResponsePost(long id, String subject, String writer, String content, LocalDate createDate, LocalDate updateDate) {
        this.id = id;
        this.subject = subject;
        this.writer = writer;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
