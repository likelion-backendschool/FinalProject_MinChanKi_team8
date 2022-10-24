package likelion.finalproject.market.post.dto.param;

import likelion.finalproject.market.post.domain.PostKeyword;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostKeywordParam {
    private long id;
    private String content;
    private LocalDate createDate;
    private LocalDate updateDate;

    @Builder
    public PostKeywordParam(long id, String content, LocalDate createDate, LocalDate updateDate) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public PostKeyword toEntity() {
        return PostKeyword.builder()
                .id(id)
                .content(content)
                .createDate(createDate)
                .updateDate(updateDate)
                .build();
    }
}
