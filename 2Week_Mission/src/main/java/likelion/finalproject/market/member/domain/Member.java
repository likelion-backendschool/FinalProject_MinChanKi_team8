package likelion.finalproject.market.member.domain;

import likelion.finalproject.global.domain.BaseTimeEntity;
import likelion.finalproject.market.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    // 작가명
    @Column(unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column
    private int cash = 0;

    @Column
    @Enumerated(EnumType.STRING)
    private Auth auth;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts;

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void setWriter() {
        this.auth = Auth.WRITER;
    }

    public void addCash(int money) {
        this.cash += money;
    }

    public void useCash(int money) {
        this.cash -= money;
    }
}