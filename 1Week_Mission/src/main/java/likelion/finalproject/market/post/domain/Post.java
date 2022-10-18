package likelion.finalproject.market.post.domain;

import likelion.finalproject.market.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String contentHtml;

    @Column(nullable = false)
    private LocalDate createDate;

    @Column(nullable = false)
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Post(String subject, String content, String contentHtml, Member member) {
        this.subject = subject;
        this.content = content;
        this.contentHtml = contentHtml;
        this.member = member;
    }

    public void setCreateDate(LocalDate date) {
        this.createDate = date;
    }

    public void setUpdateDate(LocalDate date) {
        this.updateDate = date;
    }
}
