package likelion.finalproject.market.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class PostKeyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDate createDate;

    @Column(nullable = false)
    private LocalDate updateDate;

    @Builder

    public PostKeyword(long id, String content, LocalDate createDate, LocalDate updateDate) {
        this.id = id;
        this.content = content;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
