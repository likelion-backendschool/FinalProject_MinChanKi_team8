package likelion.finalproject.market.post.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class PostHashTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private LocalDate createDate;

    @Column(nullable = false)
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne()
    @JoinColumn(name = "postKeyword_id")
    private PostKeyword postKeyword;

    @Builder
    public PostHashTag(LocalDate createDate, LocalDate updateDate, Post post, PostKeyword postKeyword) {
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.post = post;
        this.postKeyword = postKeyword;
    }
}
