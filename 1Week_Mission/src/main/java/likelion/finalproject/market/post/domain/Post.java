package likelion.finalproject.market.post.domain;

import likelion.finalproject.market.hashTag.domain.PostHashTag;
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
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false, length = 5000)
    private String content;

    @Column(nullable = false, length = 5000)
    private String contentHtml;

    @Column(nullable = false)
    private LocalDate createDate;

    @Column(nullable = false)
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<PostHashTag> postHashTags;

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

    public void setMember(Member member) {
        this.member = member;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }
}
